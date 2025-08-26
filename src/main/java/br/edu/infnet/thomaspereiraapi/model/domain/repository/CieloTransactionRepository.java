package br.edu.infnet.thomaspereiraapi.model.domain.repository;

import br.edu.infnet.thomaspereiraapi.model.domain.CieloTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CieloTransactionRepository extends JpaRepository<CieloTransaction, Integer> {
    Optional<CieloTransaction> findFirstByMerchantOrderId(String merchantOrderId);
}
