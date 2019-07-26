package com.financialhouse.merchandise.reporting.repository;

import com.financialhouse.merchandise.reporting.model.db.*;
import com.financialhouse.merchandise.reporting.model.db.enums.AcquirerType;
import com.financialhouse.merchandise.reporting.model.db.enums.Status;
import org.apache.commons.text.WordUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("integrationtest")
@ExtendWith(SpringExtension.class)
@DataJpaTest
@DisplayName("Transaction CRUD Tests")
public class TransactionRepositoryTest {

    @Autowired
    private TransactionRepository transactionRepository;

    @Test
    @DisplayName("Query Transaction with ID")
    public void testQueryTransactionWithId() {
        Optional<Transaction> byId = transactionRepository.findById(999701L);
        assertAll(
                () -> assertTrue(byId.isPresent(), "Query byId(999701L) does not return Transaction"),
                () -> assertTrue(byId.get().getStatus().equals(Status.APPROVED)),
                () -> assertTrue(byId.get().getCode().equals("00")),
                () -> assertTrue(byId.get().getReferenceNo().equalsIgnoreCase("test-transaction-1")),
                () -> assertTrue(byId.get().getChannel().equalsIgnoreCase("API"))
        );
    }

    @Test
    @DisplayName("Query Transaction with transactionId")
    public void queryWithTransactionId() {
        Optional<Transaction> queriedTransaction = transactionRepository.
                findOneByIdAndDateAndMerchantId(999702L, 1539329656L, 99921L);
        assertAll(
                () -> assertTrue(queriedTransaction.isPresent()),
                () -> assertThat(queriedTransaction.get().getReferenceNo(), is(equalTo("test-transaction-2")))
        );
    }

    @Test
    @DisplayName("Create Transaction")
    public void testCreateTransaction() {
        AcquirerTransaction acquirerTransaction = AcquirerTransaction.builder().name("RoyalPay PayToCard")
                .code("RP")
                .type(AcquirerType.PAYTOCARD)
                .acquirerTransactionId(UUID.randomUUID().toString())
                .build();

        Agent agent = Agent.builder().customerIp("123.45.67.89")
                .customerUserAgent("PostmanRuntime/1.2.3")
                .merchantIp("12.34.56.78")
                .merchantUserAgent("PostmanRuntime/3.2.1").build();

        FxTransaction fxTransaction = FxTransaction.builder().originalAmount(new BigDecimal(1500))
                .originalCurrency("RUB")
                .convertedAmount(new BigDecimal(1500))
                .convertedCurrency("RUB")
                .fxTransactionId(UUID.randomUUID().toString())
                .build();

        IPN ipn = IPN.builder().sent(true)
                .token(UUID.randomUUID().toString())
                .type("MERCHANTIPN")
                .url("https://requestb.in/10bmd651")
                .build();

        Transaction transaction = new Transaction();
        transaction.setAcquirerTransaction(acquirerTransaction);
        transaction.setAgent(agent);
        transaction.setFxTransaction(fxTransaction);
        transaction.setIpn(ipn);

        acquirerTransaction.setTransaction(transaction);
        agent.setTransaction(transaction);
        fxTransaction.setTransaction(transaction);
        ipn.setTransaction(transaction);

        transaction.setReferenceNo("trn-test-seck-1");
        transaction.setChainId(UUID.randomUUID().toString());
        transaction.setReturnUrl(null);
        transaction.setStatus(Status.APPROVED);
        transaction.setCode("00");
        transaction.setMessage(WordUtils.capitalizeFully(Status.APPROVED.name()));
        transaction.setChannel("API");
        transaction.setType("AUTH");

        Transaction savedTransaction = transactionRepository.saveAndFlush(transaction);
        assertAll(
                () -> assertNotNull(savedTransaction),
                () -> assertNotNull(savedTransaction.getCreatedAt()),
                () -> assertNotNull(savedTransaction.getModifiedAt()),
                () -> assertTrue(savedTransaction.getAcquirerTransaction().getCode().equals("RP")),
                () -> assertTrue(savedTransaction.getAgent().getCustomerIp().equals("123.45.67.89")),
                () -> assertTrue(savedTransaction.getFxTransaction().getOriginalCurrency().equals("RUB")),
                () -> assertTrue(savedTransaction.getIpn().isSent())
        );
    }

    @Test
    @DisplayName("Update Transaction")
    public void testUpdateTransaction() {
        Optional<Transaction> byId = transactionRepository.findById(999703L);
        assertTrue(byId.isPresent(), "Query byId(999703L) does not return Transaction");
        Transaction transaction = byId.get();
        Date modifiedAt = transaction.getModifiedAt();
        transaction.setReturnUrl("returnurl-updated");
        Transaction updatedTransaction = transactionRepository.saveAndFlush(transaction);
        assertAll(
                () -> assertNotNull(updatedTransaction),
                () -> assertTrue(modifiedAt.before(updatedTransaction.getModifiedAt())),
                () -> assertTrue(updatedTransaction.getReturnUrl().equals("returnurl-updated"))
        );
    }
}