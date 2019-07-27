package com.financialhouse.merchandise.reporting.controller;

import com.financialhouse.merchandise.reporting.model.db.Transaction;
import com.financialhouse.merchandise.reporting.model.rest.CustomerInfoQueryRequest;
import com.financialhouse.merchandise.reporting.model.rest.CustomerInfoQueryResponse;
import com.financialhouse.merchandise.reporting.service.TransactionService;
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
    private TransactionService transactionService;

    @PostMapping("/client")
    public ResponseEntity<?> queryCustomerInfoWithTransactionId(@RequestBody CustomerInfoQueryRequest request) {
        String transactionId = request.getTransactionId();
        Optional<Transaction> queriedTransaction = transactionService.queryTransactionWithTransactionId(transactionId);
        if (queriedTransaction.isPresent()) {
            Optional<CustomerInfoQueryResponse> convert = ModelConverter.convertToQueryCustomerInfoResponse(
                    queriedTransaction.get().getCustomerInfo());
            if (convert.isPresent()) {
                return ResponseEntity.ok(convert.get());
            }
        }

        return ResponseEntity.ok("");
    }
}