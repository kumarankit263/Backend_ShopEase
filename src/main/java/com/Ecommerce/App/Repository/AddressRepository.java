package com.Ecommerce.App.Repository;

import com.Ecommerce.App.Model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Integer> {
    @Query("SELECT a FROM Address a  WHERE a.user.userId = :userId")
    List<Address> getUserAddressList(@Param("userId") Integer userId);
}
