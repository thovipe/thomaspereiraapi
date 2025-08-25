package br.edu.infnet.thomaspereiraapi.model.service;

import br.edu.infnet.thomaspereiraapi.model.domain.Customer;
import br.edu.infnet.thomaspereiraapi.model.domain.exceptions.InvalidCustomerException;
import br.edu.infnet.thomaspereiraapi.model.domain.repository.CostumerRespository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService implements CrudService<Customer, Long> {

    private final CostumerRespository customerRespository;
    public CustomerService(CostumerRespository customerRespository) {
        this.customerRespository = customerRespository;
    }

    private void checkCustomer(Customer customer) {
        if (customer.getCpf() == null) {
            throw new InvalidCustomerException("CPF field can not be null");
        } else if (customer.getName() == null || customer.getName().isEmpty()) {
            throw new InvalidCustomerException("Name field can not be empty");
        }
        if (customer.getAddress() == null) {
            throw new InvalidCustomerException("Address field can not be null");
        }
    }


    @Override
    public Customer add(Customer customer) {
        if(customer == null) {
            throw new IllegalArgumentException("Costumer cannot be null");
        }
        checkCustomer(customer);
        if(customer.getId() != null) {
            throw new InvalidCustomerException("Id field can not set, already exists.");
        }
        return customerRespository.save(customer);
    }

    @Override
    public Customer update(Long id, Customer customer) {
        if( id == null || customer == null) {
           throw new IllegalArgumentException("Id or costumer cannot be null");
        }
        checkCustomer(customer);
        Customer customerToBeUpdated = getById(id);
        customerToBeUpdated = customer;
        customerToBeUpdated.setId(id);
        return customerRespository.save(customerToBeUpdated);
    }

    @Override
    public void delete(Long id) {
        if( id == null) {
            throw new IllegalArgumentException("Id or costumer cannot be null");
        }
        var costumer = getById(id);
        customerRespository.delete(costumer);
    }

    @Override
    public Customer getById(Long id) {
        if(id == null) {
            throw new IllegalArgumentException("Id cannot be null");
        }
        return customerRespository.findById(id).orElseThrow(
                () -> new InvalidCustomerException("Costumer with id " + id + " not found")
        );
    }

    @Override
    public List<Customer> getList() {
        return customerRespository.findAll();
    }
}
