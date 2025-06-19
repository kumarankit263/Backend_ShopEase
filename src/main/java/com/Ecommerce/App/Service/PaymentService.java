package com.Ecommerce.App.Service;

import com.Ecommerce.App.Exception.PaymentException;
import com.Ecommerce.App.Model.Payment;

public interface PaymentService {
    Payment makePayment(Integer orderId, Integer userId) throws PaymentException;
}
