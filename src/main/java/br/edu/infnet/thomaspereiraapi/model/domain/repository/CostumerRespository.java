package br.edu.infnet.thomaspereiraapi.model.domain.repository;

import br.edu.infnet.thomaspereiraapi.model.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CostumerRespository extends JpaRepository<Customer, Long> {
}
