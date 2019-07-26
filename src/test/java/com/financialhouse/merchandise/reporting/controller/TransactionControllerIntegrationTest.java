package com.financialhouse.merchandise.reporting.controller;

import com.financialhouse.merchandise.reporting.model.rest.JwtResponse;
import com.financialhouse.merchandise.reporting.model.rest.TransactionQueryRequest;
import com.financialhouse.merchandise.reporting.model.rest.TransactionQueryResponse;
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
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("integrationtest")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DisplayName("Transaction Controller Integration Tests")
@Execution(ExecutionMode.CONCURRENT)
public class TransactionControllerIntegrationTest extends AbstractControllerTest {
    @Value("${jwt.expiration}")
    private long expiration;

    @Test
    @DisplayName("Query Transaction with existing transactionId")
    public void testQueryTransactionWithTransactionId() throws URISyntaxException {
        ResponseEntity<TransactionQueryResponse> httpResponse = queryByTransactionId("999702-1539329656-99921");
        TransactionQueryResponse response = httpResponse.getBody();

        assertAll(
                () -> assertThat(httpResponse.getStatusCodeValue(), is(equalTo(200))),
                () -> assertNotNull(response.getCustomerInfo()),
                () -> assertNotNull(response.getFx()),
                () -> assertNotNull(response.getTransaction()),
                () -> assertNotNull(response.getTransaction().getTransactionMerchant()),
                () -> assertNotNull(response.getTransaction().getTransactionMerchant().getAgent()),
                () -> assertNotNull(response.getMerchant()),
                () -> assertThat(response.getCustomerInfo().getNumber(), is(equalTo(99911L))),
                () -> assertThat(response.getTransaction().getTransactionMerchant().getId(), is(equalTo(999702L)))
        );
    }

    @Test
    @DisplayName("Query Transaction with non-existing transactionId")
    public void testQueryTransactionWithNonExistingTransactionId() throws URISyntaxException {
        ResponseEntity<TransactionQueryResponse> httpResponse = queryByTransactionId("123-456-789");
        TransactionQueryResponse response = httpResponse.getBody();

        assertAll(
                () -> assertThat(httpResponse.getStatusCodeValue(), is(equalTo(200))),
                () -> assertNull(response)
        );
    }

    @Test
    @DisplayName("Query Transaction with not well-formatted split")
    public void testQueryTransactionWithNotWellFormattedSplit() throws URISyntaxException {
        ResponseEntity<TransactionQueryResponse> httpResponse = queryByTransactionId("999702_1539329656_99921");
        TransactionQueryResponse response = httpResponse.getBody();

        assertAll(
                () -> assertThat(httpResponse.getStatusCodeValue(), is(equalTo(200))),
                () -> assertNull(response)
        );
    }

    @Test
    @DisplayName("Query Transaction with not well-formatted tokens")
    public void testQueryTransactionWithNotWellFormattedTokens() throws URISyntaxException {
        ResponseEntity<TransactionQueryResponse> httpResponse = queryByTransactionId("abc-def-ghi");
        TransactionQueryResponse response = httpResponse.getBody();

        assertAll(
                () -> assertThat(httpResponse.getStatusCodeValue(), is(equalTo(200))),
                () -> assertNull(response)
        );
    }

    private ResponseEntity<TransactionQueryResponse> queryByTransactionId(String transactionId) throws URISyntaxException {
        RestTemplate restTemplate = restTemplate();
        URI uri = new URI(createURLWithPort("/transactions"));
        JwtResponse jwtResponse = login("testuser@financialhouse.com", "123456");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", jwtResponse.getToken());
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        HttpEntity entity = new HttpEntity(headers);

        TransactionQueryRequest request = new TransactionQueryRequest(transactionId);

        HttpEntity<TransactionQueryRequest> httpRequest = new HttpEntity<>(request, headers);

        return restTemplate.postForEntity(
                uri, httpRequest, TransactionQueryResponse.class);
    }
}
