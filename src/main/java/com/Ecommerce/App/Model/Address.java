package com.Ecommerce.App.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AddressID")
    private Integer addressID;

    @NotNull(message = "flatNo Name Mandatory")
    @NotBlank(message = "flatNo Name Mandatory")
    private String flatNo;

    @NotNull(message = "Street Name Mandatory")
    @NotBlank(message = "Street Name Mandatory")
    private String Street;

    @Size(max = 10)
    @NotNull(message = "City Name Mandatory")
    @NotBlank(message = "City Name Mandatory")
    private String city;

    @NotNull(message = "zipCode should not Blank")
    @NotBlank(message = "zipCode is Mandatory")
    private String ZipCode;

    @Size(max = 10)
    @NotNull(message = "State should not Blank")
    @NotBlank(message = "State Name Mandatory")
    private String state;

    @ManyToOne
    @JsonIgnore
    private User user;

    public Integer getAddressID() {
        return addressID;
    }

    public void setAddressID(Integer addressID) {
        this.addressID = addressID;
    }

    public String getFlatNo() {
        return flatNo;
    }

    public void setFlatNo(String flatNo) {
        this.flatNo = flatNo;
    }

    public String getStreet() {
        return Street;
    }

    public void setStreet(String street) {
        Street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return ZipCode;
    }

    public void setZipCode(String zipCode) {
        ZipCode = zipCode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
