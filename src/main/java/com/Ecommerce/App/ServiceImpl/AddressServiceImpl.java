package com.Ecommerce.App.ServiceImpl;

import com.Ecommerce.App.Exception.AddressException;
import com.Ecommerce.App.Model.Address;
import com.Ecommerce.App.Model.User;
import com.Ecommerce.App.Repository.AddressRepository;
import com.Ecommerce.App.Repository.UserRepository;
import com.Ecommerce.App.Service.AddressService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;
    private final UserRepository userRepository;

    public AddressServiceImpl(AddressRepository addressRepository, UserRepository userRepository) {
        this.addressRepository = addressRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Address addAddressToUser(Integer userId, Address address) throws AddressException {
        User existingUser=userRepository.findById(userId)
                .orElseThrow(()->new RuntimeException("User Not Fouund"));
        Address saveAddress=addressRepository.save(address);
        saveAddress.setUser(existingUser);
        existingUser.getAddress().add(saveAddress);
        userRepository.save(existingUser);
        return saveAddress;
    }
    @Override
    public Address updateAddress(Address address, Integer addressId) throws AddressException {
       Address existingAddress=addressRepository.findById(addressId)
               .orElseThrow(()->new AddressException("Address not found"));

       existingAddress.setFlatNo(address.getFlatNo());
       existingAddress.setZipCode(address.getZipCode());
       existingAddress.setStreet(address.getStreet());
        existingAddress.setCity(address.getCity());
        existingAddress.setState(address.getState());
        return addressRepository.save(existingAddress);
    }

    @Override
    public void removeAddress(Integer addressId) throws AddressException {
        Address existingAddress=addressRepository.findById(addressId)
                .orElseThrow(()->new AddressException("Address not found"));

        // Delete the address from the repository
        addressRepository.delete(existingAddress);
    }

    @Override
    public List<Address> getAllUserAddress(Integer userId) throws AddressException {
        List<Address> userAddressList = addressRepository.getUserAddressList(userId);
        if (userAddressList.isEmpty()) {
            System.out.println("empty");
            throw new AddressException("User does not have any address");
        }
        return userAddressList;
    }
}
