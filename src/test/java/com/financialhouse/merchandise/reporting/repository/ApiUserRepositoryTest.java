package com.financialhouse.merchandise.reporting.repository;

import com.financialhouse.merchandise.reporting.model.db.ApiUser;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles("integrationtest")
@ExtendWith(SpringExtension.class)
@DataJpaTest
public class ApiUserRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ApiUserRepository apiUserRepository;

    @Test
    public void whenFindByNameThenReturnApiUser() {
        ApiUser defaultApiUser = new ApiUser();
        defaultApiUser.setUsername("testuser@financialhouse.com");
        defaultApiUser.setPassword(passwordEncoder.encode("123456"));

        Optional<ApiUser> found = apiUserRepository.findOneByUsername(defaultApiUser.getUsername());

        assertEquals(defaultApiUser.getUsername(),
                found.get().getUsername(),
                "Problem with getting persisted apiuser");

    }
}
