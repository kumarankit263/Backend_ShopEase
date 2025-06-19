package com.Ecommerce.App.ModelDTO;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class AdminDTO {
    @NotNull(message = "Email Id Is Mandatory ,can Not Be Null")
    @NotBlank(message = "Email Id Is Mandatory")
    @Column(name = "email", unique = true)
    private String email;

    @NotNull(message = "Password is Mandatory ,can Not Be Null")
    @NotBlank(message = "Password Is Mandatory")
    @Size(max = 10,min = 5,message = "Password length should be more than 5 Character")
    private String password;

    @NotNull(message = "First can Not be Null")
    @NotBlank(message = "First Name can not be blank")
    private String firstName;

    @NotNull(message = "Last can Not be Null")
    @NotBlank(message = "Last Name can not be blank")
    private String lastName;

    @NotNull(message = "Phone Number can Not be Null")
    @NotBlank(message = "Phone Number can not be blank")
    @Size(max = 10,min = 10,message = "Phone Number should be minumum 10 digit max 12")
    private String phoneNumber;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
