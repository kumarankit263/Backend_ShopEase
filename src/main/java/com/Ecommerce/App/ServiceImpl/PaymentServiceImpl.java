package com.Ecommerce.App.ServiceImpl;

import com.Ecommerce.App.Enum.OrderStatus;
import com.Ecommerce.App.Enum.PaymentMethod;
import com.Ecommerce.App.Enum.PaymentStatus;
import com.Ecommerce.App.Exception.PaymentException;
import com.Ecommerce.App.Exception.UserException;
import com.Ecommerce.App.Model.Orders;
import com.Ecommerce.App.Model.Payment;
import com.Ecommerce.App.Model.User;
import com.Ecommerce.App.Repository.OrderRepository;
import com.Ecommerce.App.Repository.PaymentRepository;
import com.Ecommerce.App.Repository.UserRepository;
import com.Ecommerce.App.Service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.Ecommerce.App.Service.PaymentService;

import java.time.LocalDateTime;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Payment makePayment(Integer orderId, Integer userId) throws PaymentException {
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new UserException("User not found in the database."));

        Orders order = orderRepository.findById(orderId)
                .orElseThrow(() -> new UserException("order not found in the database."));;
        if (order == null) {
            throw new PaymentException("Order not found for the given customer.");
        }

        Payment payment = new Payment();
        payment.setPaymentAmount(order.getTotalAmount());
        payment.setPaymentDate(LocalDateTime.now());
        payment.setPaymentMethod(PaymentMethod.UPI);
        payment.setPaymentStatus(PaymentStatus.SUCCESSFUL);
        payment.setUser(existingUser);

        payment.setOrder(order);
        paymentRepository.save(payment);

        order.setStatus(OrderStatus.SHIPPED);

        // Set the payment for the order
        order.setPayment(payment);
        // Save the changes to the Order entity, including the associated Payment
        orderRepository.save(order);

        existingUser.getPayments().add(payment);
        // Save the changes to the User entity, including the new payment association
        userRepository.save(existingUser);
        // Save the payment to the database
        return  payment;
    }
}
