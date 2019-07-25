package com.financialhouse.merchandise.reporting.controller;

import com.financialhouse.merchandise.reporting.model.rest.CustomerInfoQueryRequest;
import com.financialhouse.merchandise.reporting.model.rest.CustomerInfoQueryResponse;
import com.financialhouse.merchandise.reporting.model.rest.JwtResponse;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("integrationtest")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DisplayName("Client Controller Integration Tests")
@Execution(ExecutionMode.CONCURRENT)
public class ClientControllerIntegrationTest extends AbstractControllerTest {
    @Value("${jwt.expiration}")
    private long expiration;

    @Test
    @Disabled("Disabled for data model creation")
    @DisplayName("Query CustomerInfo with existing transactionId")
    public void testQueryCustomerInfoWithTransactionId() throws URISyntaxException {
        ResponseEntity<CustomerInfoQueryResponse> httpResponse = queryByTransactionId("transactionId-1");
        CustomerInfoQueryResponse response = httpResponse.getBody();

        assertAll(
                () -> assertThat(httpResponse.getStatusCodeValue(), is(equalTo(200))),
                () -> assertThat(response.getNumber(), is(equalTo(4111111111111111L)))
        );
    }

    @Test
    @Disabled("Disabled for data model creation")
    @DisplayName("Query CustomerInfo with non-existing transactionId")
    public void testQueryCustomerInfoWithNonExistindTransactionId() throws URISyntaxException {
        ResponseEntity<CustomerInfoQueryResponse> httpResponse = queryByTransactionId("non-existing");
        CustomerInfoQueryResponse response = httpResponse.getBody();

        assertAll(
                () -> assertThat(httpResponse.getStatusCodeValue(), is(equalTo(200))),
                () -> assertNull(response)
        );
    }

    private ResponseEntity<CustomerInfoQueryResponse> queryByTransactionId(String transactionId) throws URISyntaxException {
        RestTemplate restTemplate = restTemplate();
        URI uri = new URI(createURLWithPort("/client"));
        JwtResponse jwtResponse = login("testuser@financialhouse.com", "123456");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", jwtResponse.getToken());
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        HttpEntity entity = new HttpEntity(headers);

        CustomerInfoQueryRequest request = new CustomerInfoQueryRequest(transactionId);

        HttpEntity<CustomerInfoQueryRequest> httpRequest = new HttpEntity<>(request, headers);

        return restTemplate.postForEntity(
                uri, httpRequest, CustomerInfoQueryResponse.class);
    }
}
