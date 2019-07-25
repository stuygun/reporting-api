package com.financialhouse.merchandise.reporting.repository;

import com.financialhouse.merchandise.reporting.model.db.Agent;
import com.financialhouse.merchandise.reporting.model.db.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AgentRepository extends JpaRepository<Agent, Long> {
    Optional<Agent> findOneByTransaction(Transaction transaction);
}