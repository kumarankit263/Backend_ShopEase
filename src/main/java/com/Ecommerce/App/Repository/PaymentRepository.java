package com.Ecommerce.App.Repository;

import com.Ecommerce.App.Model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {

}