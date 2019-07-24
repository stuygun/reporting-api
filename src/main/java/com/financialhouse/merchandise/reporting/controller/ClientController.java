package com.financialhouse.merchandise.reporting.controller;

import com.financialhouse.merchandise.reporting.model.db.CustomerInfo;
import com.financialhouse.merchandise.reporting.model.rest.CustomerInfoQueryRequest;
import com.financialhouse.merchandise.reporting.repository.CustomerInfoRepository;
import com.financialhouse.merchandise.reporting.util.ModelConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@CrossOrigin
@RestController
public class ClientController {
    @Autowired
    private CustomerInfoRepository customerInfoRepository;

    @PostMapping("/client")
    public ResponseEntity<?> queryCustomerInfoWithTransactionId(@RequestBody CustomerInfoQueryRequest request) {
        Optional<CustomerInfo> queriedCi = customerInfoRepository.findOneByTransactions_transactionId(request.getTransactionId());
        if(queriedCi.isPresent()){
            return ResponseEntity.ok(ModelConverter.convert(queriedCi.get()));
        } else {
            return ResponseEntity.ok("");
        }
    }
}