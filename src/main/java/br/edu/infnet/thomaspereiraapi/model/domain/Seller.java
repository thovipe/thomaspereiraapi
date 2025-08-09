package br.edu.infnet.thomaspereiraapi.model.domain;

public class Seller {

    private String name;
    private String email;
    private Integer sellerId;
    private boolean isActive;
    private Address address;

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

    @Override
    public String toString() {
        return String.format("SellerId: %d \nSeller: %s \ne-mail: %s \nisActive: %s \nAddress: %s", this.sellerId, this.name, this.email, this.isActive, this.address);
    }
}
