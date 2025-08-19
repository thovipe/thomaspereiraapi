package br.edu.infnet.thomaspereiraapi.model.domain.repository;

import br.edu.infnet.thomaspereiraapi.model.domain.CieloTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CieloTransactionRepository extends JpaRepository<CieloTransaction, Integer> {
}
