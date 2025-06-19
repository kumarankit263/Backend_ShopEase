package com.Ecommerce.App.ModelDTO;

public class UserSignInDetail {
    Integer id;
    String firstName;
    String lastName;
    String signinStatus;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getSigninStatus() {
        return signinStatus;
    }

    public void setSigninStatus(String signinStatus) {
        this.signinStatus = signinStatus;
    }

    public UserSignInDetail() {
    }
}
