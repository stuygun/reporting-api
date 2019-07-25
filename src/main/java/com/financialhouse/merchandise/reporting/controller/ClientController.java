package com.financialhouse.merchandise.reporting.controller;

import com.financialhouse.merchandise.reporting.model.db.CustomerInfo;
import com.financialhouse.merchandise.reporting.model.db.Transaction;
import com.financialhouse.merchandise.reporting.model.rest.CustomerInfoQueryRequest;
import com.financialhouse.merchandise.reporting.repository.CustomerInfoRepository;
import com.financialhouse.merchandise.reporting.repository.TransactionRepository;
import com.financialhouse.merchandise.reporting.util.ModelConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@CrossOrigin
@RestController
public class ClientController {
    @Autowired
    private TransactionRepository transactionRepository;

    @PostMapping("/client")
    public ResponseEntity<?> queryCustomerInfoWithTransactionId(@RequestBody CustomerInfoQueryRequest request) {
        String transactionId = request.getTransactionId();
        String[] split = transactionId.split("-");
        long id = Long.parseLong(split[0]);
        long date = Long.parseLong(split[1]);
        long merchantId = Long.parseLong(split[2]);
        Optional<Transaction> queriedTransaction = transactionRepository.findOneByIdAndDateAndMerchantId(id, date, merchantId);
        if (queriedTransaction.isPresent()) {
            return ResponseEntity.ok(ModelConverter.convert(queriedTransaction.get().getCustomerInfo()));
        } else {
            return ResponseEntity.ok("");
        }
    }
}