package com.Ecommerce.App.Exception;

public class PaymentException extends RuntimeException {

    public PaymentException() {

    }
    public PaymentException(String msg) {
        super(msg);
    }


}