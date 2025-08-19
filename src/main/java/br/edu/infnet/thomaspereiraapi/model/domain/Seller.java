package br.edu.infnet.thomaspereiraapi.model.domain;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Seller {

    private String name;
    private String email;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer sellerId;
    private boolean isActive;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;
    @ManyToOne(cascade = CascadeType.ALL)
    private CieloPaymentProvider cieloPaymentProvider;

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Seller() {
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getSellerId() {
        return this.sellerId;
    }

    public void setSellerId(Integer sellerId) {
        this.sellerId = sellerId;
    }

    public boolean getIsActive() {
        return this.isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public Address getAddress() {
        return this.address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public CieloPaymentProvider getCieloPaymentProvider() {
        return this.cieloPaymentProvider;
    }

    public void setCieloPaymentProvider(CieloPaymentProvider cieloPaymentProvider) {
        this.cieloPaymentProvider = cieloPaymentProvider;
    }

    @Override
    public String toString() {
        return String.format("SellerId: %d \nSeller: %s \ne-mail: %s \nisActive: %s \nAddress: %s", this.sellerId, this.name, this.email, this.isActive, this.address);
    }
}

