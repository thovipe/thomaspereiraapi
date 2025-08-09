package br.edu.infnet.thomaspereiraapi.model.domain;

public class Costumer {

    private String name;
    private String email;
    private String phone;
    private Address address;
    private Integer costumerId;

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

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Address getAddress() {
        return this.address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Integer getCostumerId() {
        return this.costumerId;
    }

    public void setCostumerId(Integer costumerId) {
        this.costumerId = costumerId;
    }

    @Override
    public String toString() {
        return String.format("costumerId: %d - name: %s - email: %s - phone: %s - address: %s", this.costumerId, this.name, this.email, this.phone, this.address);
    }

}
