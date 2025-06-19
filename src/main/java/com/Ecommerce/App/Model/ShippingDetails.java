package com.Ecommerce.App.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
@Entity
public class ShippingDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shipping_id")
    private Long shippingId;

    @NotNull(message = "Address Is Mandatory ,can Not Be Null")
    @NotBlank(message = "Address Is Mandatory")
    @Column(name = "address")
    private String address;

    @NotNull(message = "City Is Mandatory ,can Not Be Null")
    @NotBlank(message = "City Is Mandatory")
    @Column(name = "city")
    private String city;

    @NotNull(message = "State Is Mandatory ,can Not Be Null")
    @NotBlank(message = "State Is Mandatory")
    @Column(name = "state")
    private String state;

    @Column(name = "country")
    private String country;

    @NotNull(message = "Postal Code Is Mandatory ,can Not Be Null")
    @NotBlank(message = "Postal Code Is Mandatory")
    @Column(name = "postal_code")
    private String postalCode;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "shipper_id")
    private Shipper shipper;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "order_id")
    private Orders orders;

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public Long getShippingId() {
        return shippingId;
    }

    public void setShippingId(Long shippingId) {
        this.shippingId = shippingId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Shipper getShipper() {
        return shipper;
    }

    public void setShipper(Shipper shipper) {
        this.shipper = shipper;
    }

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }
}
