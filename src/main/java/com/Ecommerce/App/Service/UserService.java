package com.Ecommerce.App.Service;

import com.Ecommerce.App.Exception.UserException;
import com.Ecommerce.App.Model.User;
import com.Ecommerce.App.ModelDTO.AdminDTO;
import com.Ecommerce.App.ModelDTO.CustomerDTO;
import com.Ecommerce.App.ModelDTO.UserDTO;

import java.util.List;

public interface UserService {
    public User getUserByEmailId(String emailId)throws UserException;

    public User addUser(CustomerDTO customer)  throws UserException;

    public User addUserAdmin(AdminDTO admin	)  throws UserException;

    public User changePassword(Integer userId, UserDTO customer)  throws UserException;

    public String deactivateUser(Integer userId) throws UserException;

    public User getUserDetails(Integer userId)throws UserException;

    public List<User> getAllUserDetails() throws UserException;
}
