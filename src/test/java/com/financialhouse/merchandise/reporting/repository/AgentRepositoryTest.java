package com.financialhouse.merchandise.reporting.repository;

import com.financialhouse.merchandise.reporting.model.db.Agent;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("integrationtest")
@ExtendWith(SpringExtension.class)
@DataJpaTest
@DisplayName("Agent CRUD Tests")
public class AgentRepositoryTest {

    @Autowired
    private AgentRepository agentRepository;

    @Test
    @DisplayName("Query Agent with ID")
    public void testQueryAgentWithId() {
        Optional<Agent> byId = agentRepository.findById(999401L);
        assertAll(
                () -> assertTrue(byId.isPresent(), "Query byId(999401L) does not return Agent"),
                () -> assertTrue(byId.get().getCustomerIp().equalsIgnoreCase("213.74.172.226")),
                () -> assertTrue(byId.get().getCustomerUserAgent().equalsIgnoreCase("PostmanRuntime/7.3.0")),
                () -> assertTrue(byId.get().getMerchantIp().equalsIgnoreCase("213.74.172.226")),
                () -> assertTrue(byId.get().getMerchantUserAgent().equalsIgnoreCase("PostmanRuntime/7.3.0"))
        );
    }

    @Test
    @DisplayName("Update Agent")
    public void testUpdateAgent() {
        Optional<Agent> byId = agentRepository.findById(999401L);
        assertTrue(byId.isPresent(), "Query byId(999401L) does not return Agent");
        Agent agent = byId.get();
        Date modifiedAt = agent.getModifiedAt();
        agent.setCustomerIp("213.74.172.225");
        Agent updatedAgent = agentRepository.saveAndFlush(agent);
        assertAll(
                () -> assertNotNull(updatedAgent),
                () -> assertTrue(modifiedAt.before(updatedAgent.getModifiedAt())),
                () -> assertTrue(updatedAgent.getCustomerIp().equals("213.74.172.225"))
        );
    }
}