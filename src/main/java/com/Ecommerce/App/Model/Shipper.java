package com.Ecommerce.App.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Shipper {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer shipperId;

    @NotNull(message = "Name Is Mandatory ,can Not Be Null")
    @NotBlank(message = "Name Is Mandatory")
    @Column(name = "name")
    private String name;

    @NotNull(message = "phoneNumber Is Mandatory ,can Not Be Null")
    @NotBlank(message = "phoneNumber Is Mandatory")
    @Size(min=10,max = 12)
    @Column(name = "phoneNumber")
    private String phoneNumber;

    @JsonIgnore
    @OneToMany(mappedBy = "shipper",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<ShippingDetails> shippingDetails= new ArrayList<>();;

    public Integer getShipperId() {
        return shipperId;
    }

    public void setShipperId(Integer shipperId) {
        this.shipperId = shipperId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<ShippingDetails> getShippingDetails() {
        return shippingDetails;
    }

    public void setShippingDetails(List<ShippingDetails> shippingDetails) {
        this.shippingDetails = shippingDetails;
    }
}
