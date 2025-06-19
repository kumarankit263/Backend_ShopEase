package com.Ecommerce.App.Controller;

import com.Ecommerce.App.Exception.UserException;
import com.Ecommerce.App.ModelDTO.UserSignInDetail;
import com.Ecommerce.App.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ecom")
public class LoginController {
    @Autowired
    private UserService userService;

    @GetMapping("/signIn")
    public ResponseEntity<UserSignInDetail> getLoggedInCustomerDetailsHandler(Authentication auth) {
        try {var customer = userService.getUserByEmailId(auth.getName());
            System.out.println("auth anme is " + auth.getName());
            UserSignInDetail signinSuceesData = new UserSignInDetail();
            signinSuceesData.setId(customer.getUserId());
            signinSuceesData.setFirstName(customer.getFirstName());
            signinSuceesData.setLastName(customer.getLastName());
            signinSuceesData.setSigninStatus("Success");

            return new ResponseEntity<>(signinSuceesData, HttpStatus.OK);}
        catch(UserException ex ){
            throw new UserException(" Invalid Password");
        }

    }
}
