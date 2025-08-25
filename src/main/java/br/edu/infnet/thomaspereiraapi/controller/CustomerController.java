package br.edu.infnet.thomaspereiraapi.controller;

import br.edu.infnet.thomaspereiraapi.model.domain.Customer;
import br.edu.infnet.thomaspereiraapi.model.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<List<Customer>> GetAllCustomers() {
        return ResponseEntity.ok(customerService.getList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> GetCustomer(@PathVariable Long id) {
        return ResponseEntity.ok(customerService.getById(id));
    }

    @PostMapping
    public ResponseEntity<Customer> addCustomer(@Valid @RequestBody Customer customer) {
        Customer newcustomer = customerService.add(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(newcustomer);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id,@Valid @RequestBody Customer customer) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(customerService.update(id, customer));
    }


}
