package com.financialhouse.merchandise.reporting.repository;

import com.financialhouse.merchandise.reporting.model.db.CustomerInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerInfoRepository extends JpaRepository<CustomerInfo, Long> {
    Optional<CustomerInfo> findOneByTransactions_transactionId(String transactionId);
}