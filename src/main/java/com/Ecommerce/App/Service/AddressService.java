package com.Ecommerce.App.Service;

import com.Ecommerce.App.Exception.AddressException;
import com.Ecommerce.App.Model.Address;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface AddressService {
    public Address addAddressToUser(Integer userId, Address address) throws AddressException;

    public Address updateAddress( Address address, Integer addressId)  throws AddressException;

    public void removeAddress(Integer addressId)  throws AddressException;

    public List<Address> getAllUserAddress(Integer userId)  throws AddressException;


}
