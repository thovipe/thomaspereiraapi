package br.edu.infnet.thomaspereiraapi.model.domain.repository;

import br.edu.infnet.thomaspereiraapi.model.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CostumerRespository extends JpaRepository<Customer, Long> {
    Optional<Customer> findCustomerByCpf(String cpf);
}
