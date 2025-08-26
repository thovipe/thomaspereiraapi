package br.edu.infnet.thomaspereiraapi.model.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Seller {
    @NotBlank(message = "Seller name is mandatory field.")
    private String name;
    @NotBlank(message = "Seller email is a mandatory field.")
    @Email(message = "Invalid email format.")
    private String email;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private boolean isActive;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    @Valid
    private Address address;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<CieloTransaction> cieloTransactions;
    @NotBlank(message = "The cnpj field is mandatory.")
    @Pattern(regexp = "^\\d{2}\\.\\d{3}\\.\\d{3}/\\d{4}-\\d{2}$", message = "Invalid CNPJ, use the pattern XX.XXX.XXX/XXX-XX.")
    private String cnpj;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "seller")
    @JsonManagedReference
    private List<Customer> customer = new ArrayList();

    public boolean getIsActive() {
        return this.isActive;
    }

    public void setIsActive(boolean active) {
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

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }




    public Address getAddress() {
        return this.address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<CieloTransaction> getCieloTransactions() {
        return this.cieloTransactions;
    }
    public void setTransactions(List<CieloTransaction> cieloTransactions) {
        this.cieloTransactions = cieloTransactions;
    }

    public String getCnpj() {
        return this.cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public List<Customer> getCustomer() {
        return this.customer;
    }

    public void setCustomer(List<Customer> customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return String.format("SellerId: %d \nSeller: %s \ne-mail: %s \nisActive: %s \nAddress: %s", this.id, this.name, this.email, this.isActive, this.address);
    }
}

