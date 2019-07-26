package com.financialhouse.merchandise.reporting.service;

import com.financialhouse.merchandise.reporting.model.db.Transaction;
import com.financialhouse.merchandise.reporting.repository.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public Optional<Transaction> queryTransactionWithTransactionId(String transactionId) {
        String[] split = transactionId.split("-");
        if (split.length == 3) {
            try {
                long id = Long.parseLong(split[0]);
                long date = Long.parseLong(split[1]);
                long merchantId = Long.parseLong(split[2]);
                return transactionRepository.findOneByIdAndDateAndMerchantId(id, date, merchantId);
            } catch (NumberFormatException e){
                log.error("transactionId[" + transactionId + "] is not well formatted", e);
            }
        }
        return Optional.empty();
    }
}