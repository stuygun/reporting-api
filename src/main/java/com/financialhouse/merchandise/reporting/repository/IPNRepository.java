package com.financialhouse.merchandise.reporting.repository;

import com.financialhouse.merchandise.reporting.model.db.IPN;
import com.financialhouse.merchandise.reporting.model.db.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IPNRepository extends JpaRepository<IPN, Long> {
    Optional<IPN> findOneByTransaction(Transaction transaction);
}