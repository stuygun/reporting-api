package com.financialhouse.merchandise.reporting.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.financialhouse.merchandise.reporting.exceptions.AuthenticationErrorResponse;
import com.financialhouse.merchandise.reporting.model.db.enums.Status;
import com.financialhouse.merchandise.reporting.model.rest.JwtResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.isEmptyString;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.jupiter.api.Assertions.assertAll;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("integrationtest")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DisplayName("Merchant Controller Integration Tests")
@Execution(ExecutionMode.CONCURRENT)
public class MerchantControllerIntegrationTest extends AbstractControllerTest {
    @Value("${jwt.expiration}")
    private long expiration;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("Login Failed - Without Input")
    public void testLoginFailedWithoutInput() throws URISyntaxException, IOException {
        try {
            login(null, null);
        } catch (HttpStatusCodeException e) {
            AuthenticationErrorResponse aer = objectMapper.readValue(e.getResponseBodyAsString(), AuthenticationErrorResponse.class);
            assertAll(
                    () -> assertThat(e.getRawStatusCode(), is(equalTo(500))),
                    () -> assertThat(aer.getCode().intValue(), is(equalTo(0))),
                    () -> assertThat(aer.getStatus(), is(equalTo(Status.DECLINED))),
                    () -> assertThat(aer.getMessage(), is(equalTo("Error: Merchant User credentials is not valid")))
            );
        }
    }

    @Test
    @DisplayName("Login Failed - Invalid Password")
    public void testLoginFailedDueToInvalidPassword() throws URISyntaxException, IOException {
        try {
            login("testuser@financialhouse.com", "123457");
        } catch (HttpStatusCodeException e) {
            AuthenticationErrorResponse aer = objectMapper.readValue(e.getResponseBodyAsString(), AuthenticationErrorResponse.class);
            assertAll(
                    () -> assertThat(e.getRawStatusCode(), is(equalTo(500))),
                    () -> assertThat(aer.getCode().intValue(), is(equalTo(0))),
                    () -> assertThat(aer.getStatus(), is(equalTo(Status.DECLINED))),
                    () -> assertThat(aer.getMessage(), is(equalTo("Error: Merchant User credentials is not valid")))
            );
        }
    }

    @Test
    @DisplayName("Login Failed - Non-Existing User")
    public void testLoginFailedDueToNonExistingUser() throws URISyntaxException, IOException {
        try {
            login("abc@mail.com", "123456");
        } catch (HttpStatusCodeException e) {
            AuthenticationErrorResponse aer = objectMapper.readValue(e.getResponseBodyAsString(), AuthenticationErrorResponse.class);
            assertAll(
                    () -> assertThat(e.getRawStatusCode(), is(equalTo(500))),
                    () -> assertThat(aer.getCode().intValue(), is(equalTo(0))),
                    () -> assertThat(aer.getStatus(), is(equalTo(Status.DECLINED))),
                    () -> assertThat(aer.getMessage(), is(equalTo("Error: Merchant User credentials is not valid")))
            );
        }
    }

    @Test
    @DisplayName("Login Success")
    public void testLoginSuccess() throws URISyntaxException {
        JwtResponse jwtResponse = login("testuser@financialhouse.com", "123456");
        assertAll(
                () -> assertThat(jwtResponse.getToken(), not(isEmptyString())),
                () -> assertThat(jwtResponse.getStatus(), is(equalTo(Status.APPROVED)))
        );
    }

    @Test
    @DisplayName("Call Secured Endpoint without token")
    public void testWithoutToken() throws URISyntaxException, IOException {
        RestTemplate restTemplate = restTemplate();
        URI uri = new URI(createURLWithPort("/test"));
        try {
            String body = restTemplate.getForEntity(uri, String.class).getBody();
        } catch (HttpStatusCodeException e) {
            AuthenticationErrorResponse aer = objectMapper.readValue(e.getResponseBodyAsString(), AuthenticationErrorResponse.class);
            assertAll(
                    () -> assertThat(e.getRawStatusCode(), is(equalTo(500))),
                    () -> assertThat(aer.getStatus(), is(equalTo(Status.DECLINED))),
                    () -> assertThat(aer.getMessage(), is(equalTo("Token is required")))
            );
        }
    }

    @Test
    @DisplayName("Call Secured Endpoint with valid token")
    public void testWithToken() throws URISyntaxException {
        RestTemplate restTemplate = restTemplate();
        URI uri = new URI(createURLWithPort("/test"));
        JwtResponse jwtResponse = login("testuser@financialhouse.com", "123456");
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", jwtResponse.getToken());
        HttpEntity entity = new HttpEntity(headers);
        ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);

        assertAll(
                () -> assertThat(response.getBody(), is(equalTo("It is secured now!"))),
                () -> assertThat(response.getStatusCodeValue(), is(equalTo(200)))
        );
    }

    @Test
    @DisplayName("Call Secured Endpoint with expired token")
    public void testWithExpiredToken() throws URISyntaxException, IOException, InterruptedException {
        JwtResponse jwtResponse = login("testuser@financialhouse.com", "123456");

        RestTemplate restTemplate = restTemplate();
        URI uri = new URI(createURLWithPort("/test"));
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", jwtResponse.getToken());
        HttpEntity entity = new HttpEntity(headers);

        Thread.sleep((expiration + 2) * 1000);

        try {
            ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);
        } catch (HttpStatusCodeException e) {
            AuthenticationErrorResponse aer = objectMapper.readValue(e.getResponseBodyAsString(), AuthenticationErrorResponse.class);

            assertAll(
                    () -> assertThat(e.getRawStatusCode(), is(equalTo(500))),
                    () -> assertThat(aer.getCode().intValue(), is(equalTo(0))),
                    () -> assertThat(aer.getStatus(), is(equalTo(Status.DECLINED))),
                    () -> assertThat(aer.getMessage(), is(equalTo("Token Expired")))
            );
        }
    }
}
