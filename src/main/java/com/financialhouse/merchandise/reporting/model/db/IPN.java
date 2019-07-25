package com.financialhouse.merchandise.reporting.model.db;

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
@Table(name = "IPN")
public class IPN {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private boolean sent;
    private String url;
    private String type;
    private String token;

    @OneToOne(mappedBy = "ipn")
    private Transaction transaction;

}
