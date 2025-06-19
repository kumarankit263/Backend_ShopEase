package com.Ecommerce.App.Repository;

import com.Ecommerce.App.Model.ShippingDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShippingRepository extends JpaRepository<ShippingDetails, Integer> {

}
