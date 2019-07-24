package com.financialhouse.merchandise.reporting.model.db;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "TRANSACTION")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String transactionId;

    @ManyToOne
    @JoinColumn(name = "CUSTOMER_INFO_ID")
    private CustomerInfo customerInfo;
}
