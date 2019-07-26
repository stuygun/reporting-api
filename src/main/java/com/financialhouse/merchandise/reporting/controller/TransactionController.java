package com.financialhouse.merchandise.reporting.controller;

import com.financialhouse.merchandise.reporting.model.db.Transaction;
import com.financialhouse.merchandise.reporting.model.rest.TransactionQueryRequest;
import com.financialhouse.merchandise.reporting.model.rest.TransactionQueryResponse;
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
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @PostMapping("/transactions")
    public ResponseEntity<?> queryTransactionWithTransactionId(@RequestBody TransactionQueryRequest request) {
        String transactionId = request.getTransactionId();
        Optional<Transaction> queriedTransaction = transactionService.queryTransactionWithTransactionId(transactionId);
        if (queriedTransaction.isPresent()) {
            Optional<TransactionQueryResponse> convert = ModelConverter.convertToQueryTransactionResponse(queriedTransaction.get());
            if(convert.isPresent()){
                return ResponseEntity.ok(convert.get());
            }
        }

        return ResponseEntity.ok("");
    }

    //TODO: @PostMapping("/transactions/report")
    //TODO: @PostMapping("/transactions/list")
}