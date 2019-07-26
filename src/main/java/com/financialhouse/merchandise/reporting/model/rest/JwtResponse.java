package com.financialhouse.merchandise.reporting.model.rest;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.financialhouse.merchandise.reporting.model.db.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({"status", "token"})
public class JwtResponse {
    @JsonProperty("status")
    private Status status;
    @JsonProperty("token")
    private String token;
}