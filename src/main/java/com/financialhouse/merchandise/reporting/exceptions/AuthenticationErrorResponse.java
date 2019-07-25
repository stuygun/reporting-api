package com.financialhouse.merchandise.reporting.exceptions;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.financialhouse.merchandise.reporting.model.db.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuthenticationErrorResponse {
    private final Status status;
    private final String message;
    private Integer code;
}
