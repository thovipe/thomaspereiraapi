package br.edu.infnet.thomaspereiraapi.model.domain.repository;

import br.edu.infnet.thomaspereiraapi.model.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
}
