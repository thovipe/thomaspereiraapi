package br.edu.infnet.thomaspereiraapi.model.domain;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Costumer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String phone;
    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;
    private Integer costumerId;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "cielo_transaction_id")
    private List<CieloTransaction> cieloTransactions;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<CieloTransaction> getCieloTransactions() {
        return cieloTransactions;
    }

    public void setCieloTransactions(List<CieloTransaction> cieloTransactions) {
        this.cieloTransactions = cieloTransactions;
    }
}
