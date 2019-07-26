package com.financialhouse.merchandise.reporting.repository;

import com.financialhouse.merchandise.reporting.model.db.Transaction;
import com.financialhouse.merchandise.reporting.model.db.enums.Status;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;
import java.util.Optional;

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