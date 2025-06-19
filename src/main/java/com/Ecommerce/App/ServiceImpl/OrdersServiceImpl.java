package com.Ecommerce.App.ServiceImpl;

import com.Ecommerce.App.Exception.OrdersException;
import com.Ecommerce.App.Model.Orders;
import com.Ecommerce.App.ModelDTO.OrdersDTO;
import com.Ecommerce.App.Repository.*;
import com.Ecommerce.App.Service.OrdersService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrdersServiceImpl implements OrdersService {

    private final OrderRepository orderRepository;

    private final UserRepository userRepository;

    private final OrderItemRepository orderItemRepository;
    private final ProductRepository productRepository;
    private final CartItemRepository cartItemRepository;

    private final CartRepository cartRepository;

    public OrdersServiceImpl(OrderRepository orderRepository, UserRepository userRepository, OrderItemRepository orderItemRepository, ProductRepository productRepository, CartItemRepository cartItemRepository, CartRepository cartRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.orderItemRepository = orderItemRepository;
        this.productRepository = productRepository;
        this.cartItemRepository = cartItemRepository;
        this.cartRepository = cartRepository;
    }


    @Override
    public OrdersDTO placeOrder(Integer userid) throws OrdersException {
        return null;
    }

    @Override
    public Orders getOrdersDetails(Integer orderId) throws OrdersException {
        return null;
    }

    @Override
    public List<Orders> viewAllOrderByDate(Date date) throws OrdersException {
        return List.of();
    }

    @Override
    public void deleteOrders(Integer userId, Integer orderId) throws OrdersException {

    }
}
