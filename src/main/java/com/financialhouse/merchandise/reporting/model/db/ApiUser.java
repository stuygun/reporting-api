package com.financialhouse.merchandise.reporting.model.db;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "API_USER")
public class ApiUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
}