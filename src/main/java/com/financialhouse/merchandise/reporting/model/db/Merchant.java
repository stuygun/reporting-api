package com.financialhouse.merchandise.reporting.model.db;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "MERCHANT")
public class Merchant {
    @OneToMany(mappedBy = "merchant", cascade = CascadeType.ALL)
    List<Transaction> transactions;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private boolean allowedPartialRefund;
    private boolean allowPartialCapture;

}
