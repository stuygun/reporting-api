package com.financialhouse.merchandise.reporting.model.db;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(access = AccessLevel.PUBLIC)
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
