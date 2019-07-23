package com.financialhouse.merchandise.reporting.repository;

import com.financialhouse.merchandise.reporting.model.ApiUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ApiUserRepository extends JpaRepository<ApiUser, Long> {
    Optional<ApiUser> findOneByUsername(String username);
}