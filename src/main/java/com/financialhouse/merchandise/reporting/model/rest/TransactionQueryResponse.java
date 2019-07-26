package com.financialhouse.merchandise.reporting.model.rest;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.financialhouse.merchandise.reporting.model.db.enums.Operation;
import com.financialhouse.merchandise.reporting.model.db.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionQueryResponse {
    @JsonProperty("customerInfo")
    private CustomerInfoJson customerInfo;

    @JsonProperty("fx")
    private Fx fx;

    @JsonProperty("transaction")
    private Transaction transaction;

    @JsonProperty("merchant")
    private Merchant merchant;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    class Fx {
        @JsonProperty("merchant")
        private FxMerchant fxMerchant;

        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        class FxMerchant{
            private String originalAmount;
            private String originalCurrency;
        }
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    class Transaction {
        @JsonProperty("merchant")
        private TransactionMerchant transactionMerchant;

        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        class TransactionMerchant {
            private Long id;
            private String referenceNo;
            private Long merchantId;
            private String fxTransactionId;
            private String acquirerTransactionId;
            private Long agentInfoId;
            private String returnUrl;
            private Status status;
            private Operation operation;
            @JsonProperty("created_at")
            @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
            private Date createdAt;
            @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
            @JsonProperty("updated_at")
            private Date modifiedAt;
            @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
            @JsonProperty("deleted_at")
            private Date deletedAt;
            private String code;
            private String message;
            private String channel;
            private String customData;
            private Long parentId;
            private String type;
            private String transactionId;
            private Agent agent;

            @Data
            @NoArgsConstructor
            @AllArgsConstructor
            class Agent {
                private Long id;
                private String customerId;
                private String customerUserAgent;
                private String merchantIp;
                private String merchantUserAgent;
                @JsonProperty("created_at")
                @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
                private Date createdAt;
                @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
                @JsonProperty("updated_at")
                private Date modifiedAt;
                @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
                @JsonProperty("deleted_at")
                private Date deletedAt;
            }
        }
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    class Merchant {
        private String name;
    }
}