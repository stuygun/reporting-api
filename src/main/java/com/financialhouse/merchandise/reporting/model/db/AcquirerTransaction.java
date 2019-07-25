package com.financialhouse.merchandise.reporting.model.db;

import com.financialhouse.merchandise.reporting.model.db.enums.AcquirerType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ACQUIRER_TRANSACTION")
public class AcquirerTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String code;
    private AcquirerType type;
    private String acquirerTransactionId;

    @OneToOne(mappedBy = "acquirerTransaction")
    private Transaction transaction;
}
