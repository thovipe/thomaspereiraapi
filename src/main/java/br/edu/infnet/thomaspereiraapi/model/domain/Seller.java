package br.edu.infnet.thomaspereiraapi.model.domain;

public class Seller {

    private String name;
    private String email;
    private int sellerId;
    private boolean isActive;

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

    public int getSellerId() {
        return this.sellerId;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }

    public boolean getIsActive() {
        return this.isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    @Override
    public String toString() {
        return String.format("SellerId: %d \nSeller: %s \ne-mail: %s \nisActive: %s", this.sellerId, this.name, this.email, this.isActive);
    }
}
