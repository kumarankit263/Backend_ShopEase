package com.Ecommerce.App.Controller;


import com.Ecommerce.App.Model.Payment;
import com.Ecommerce.App.Service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ecom/order-payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/makePayment")
    public ResponseEntity<Payment> makePayment(@RequestParam Integer orderId, @RequestParam Integer userId
    ) {
        Payment payment = paymentService.makePayment(orderId, userId);
        return ResponseEntity.ok(payment);
    }

}
