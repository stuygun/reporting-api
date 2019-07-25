package com.financialhouse.merchandise.reporting.repository;

import com.financialhouse.merchandise.reporting.model.db.FxTransaction;
import com.financialhouse.merchandise.reporting.model.db.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FxTransactionRepository extends JpaRepository<FxTransaction, Long> {
    Optional<FxTransaction> findOneByTransaction(Transaction transaction);
}