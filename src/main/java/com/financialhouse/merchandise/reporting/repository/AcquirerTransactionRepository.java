package com.financialhouse.merchandise.reporting.repository;

import com.financialhouse.merchandise.reporting.model.db.AcquirerTransaction;
import com.financialhouse.merchandise.reporting.model.db.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AcquirerTransactionRepository extends JpaRepository<AcquirerTransaction, Long> {
    Optional<AcquirerTransaction> findOneByTransaction(Transaction transaction);
}