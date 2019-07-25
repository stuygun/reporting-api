package com.financialhouse.merchandise.reporting.repository;

import com.financialhouse.merchandise.reporting.model.db.CustomerInfo;
import com.financialhouse.merchandise.reporting.model.db.Transaction;
import com.financialhouse.merchandise.reporting.model.db.enums.Gender;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ActiveProfiles("integrationtest")
@ExtendWith(SpringExtension.class)
@DataJpaTest
@DisplayName("CustomerInfo CRUD Tests")
public class CustomerInfoRepositoryTest {

    @Autowired
    private CustomerInfoRepository customerInfoRepository;

    @Test
    @DisplayName("Basic CustomerInfo Save With Builder")
    public void testSaveCustomerInfo() {
        CustomerInfo ci = generateCustomerInfo();
        CustomerInfo saveCi = customerInfoRepository.save(ci);

        assertAll(
                () -> assertThat(saveCi.getNumber(), is(equalTo(ci.getNumber()))),
                () -> assertThat(saveCi.getExpiryMonth(), is(equalTo(ci.getExpiryMonth()))),
                () -> assertThat(saveCi.getExpiryYear(), is(equalTo(ci.getExpiryYear()))),
                () -> assertThat(saveCi.getEmail(), is(equalTo(ci.getEmail()))),
                () -> assertThat(saveCi.getBillingTitle(), is(equalTo(ci.getBillingTitle()))),
                () -> assertThat(saveCi.getBillingFirstName(), is(equalTo(ci.getBillingFirstName()))),
                () -> assertThat(saveCi.getBillingLastName(), is(equalTo(ci.getBillingLastName()))),
                () -> assertThat(saveCi.getBillingCompany(), is(equalTo(ci.getBillingCompany()))),
                () -> assertThat(saveCi.getBillingAddress1(), is(equalTo(ci.getBillingAddress1()))),
                () -> assertThat(saveCi.getBillingCity(), is(equalTo(ci.getBillingCity()))),
                () -> assertThat(saveCi.getBillingPostcode(), is(equalTo(ci.getBillingPostcode()))),
                () -> assertThat(saveCi.getBillingState(), is(equalTo(ci.getBillingState()))),
                () -> assertThat(saveCi.getBillingCountry(), is(equalTo(ci.getBillingCountry()))),
                () -> assertThat(saveCi.getBillingPhone(), is(equalTo(ci.getBillingPhone()))),
                () -> assertThat(saveCi.getStartMonth(), is(equalTo(ci.getStartMonth()))),
                () -> assertThat(saveCi.getIssueNumber(), is(equalTo(ci.getIssueNumber()))),
                () -> assertThat(saveCi.getBirthday(), is(equalTo(ci.getBirthday()))),
                () -> assertThat(saveCi.getGender(), is(equalTo(ci.getGender()))),
                () -> assertThat(saveCi.getBillingAddress2(), is(equalTo(ci.getBillingAddress2()))),
                () -> assertThat(saveCi.getBillingFax(), is(equalTo(ci.getBillingFax()))),
                () -> assertThat(saveCi.getShippingTitle(), is(equalTo(ci.getShippingTitle()))),
                () -> assertThat(saveCi.getShippingFirstName(), is(equalTo(ci.getShippingFirstName()))),
                () -> assertThat(saveCi.getShippingLastName(), is(equalTo(ci.getShippingLastName()))),
                () -> assertThat(saveCi.getShippingCompany(), is(equalTo(ci.getShippingCompany()))),
                () -> assertThat(saveCi.getShippingAddress1(), is(equalTo(ci.getShippingAddress1()))),
                () -> assertThat(saveCi.getShippingAddress2(), is(equalTo(ci.getShippingAddress2()))),
                () -> assertThat(saveCi.getShippingCity(), is(equalTo(ci.getShippingCity()))),
                () -> assertThat(saveCi.getShippingPostcode(), is(equalTo(ci.getShippingPostcode()))),
                () -> assertThat(saveCi.getShippingState(), is(equalTo(ci.getShippingState()))),
                () -> assertThat(saveCi.getShippingCountry(), is(equalTo(ci.getShippingCountry()))),
                () -> assertThat(saveCi.getShippingPhone(), is(equalTo(ci.getShippingPhone()))),
                () -> assertThat(saveCi.getShippingFax(), is(equalTo(ci.getShippingFax()))),
                () -> assertThat(saveCi.getToken(), is(equalTo(ci.getToken())))
        );
    }

//    @Test
//    @Disabled("Disabled for data model creation")
//    @DisplayName("Query CustomerInfo with transactionId")
//    public void queryWithTransactionId() {
//        CustomerInfo ci = generateCustomerInfo();
//
//        Transaction transaction = new Transaction();
//        //transaction.setTransactionId("transactionId-2");
//        transaction.setCustomerInfo(ci);
//        List<Transaction> transactions = new ArrayList<>();
//        transactions.add(transaction);
//
//        ci.setTransactions(transactions);
//
//        customerInfoRepository.save(ci);
//        Optional<CustomerInfo> queriedCi = customerInfoRepository.findOneByTransactions_transactionId("transactionId-2");
//        assertAll(
//                () -> assertTrue(queriedCi.isPresent()),
//                () -> assertThat(queriedCi.get().getNumber(), is(equalTo(ci.getNumber())))
//        );
//    }

    private CustomerInfo generateCustomerInfo() {
        return new CustomerInfo.Builder(4111111111111111L, (short) 1, (short) 2020, "seckin@bumin.io",
                "Mr.", "SECKIN", "SEN", "BUMIN", "BUMN",
                "ANTALYA", "07070", "ANTALYA", "TR", "05554443322")
                .setStartMonth((short) 1)
                .setStartYear((short) 2019)
                .setIssueNumber(123456L)
                .setBirthday(new Date())
                .setGender(Gender.MALE)
                .setBillingAddress2("SECK")
                .setBillingFax("05554443322")
                .setShippingTitle("Mr.")
                .setShippingFirstName("SECKIN")
                .setShippingLastName("SEN")
                .setShippingCompany("BUMIN")
                .setShippingAddress1("BUMN")
                .setShippingAddress2("BUMN")
                .setShippingCity("ANKARA")
                .setShippingPostcode("06060")
                .setShippingState("ANKARA")
                .setShippingCountry("TR")
                .setShippingPhone("05554443322")
                .setShippingFax("05554443322")
                .setToken("1t2o3k4e5n")
                .build();
    }
}