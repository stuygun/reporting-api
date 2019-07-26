package com.financialhouse.merchandise.reporting.util;

import com.financialhouse.merchandise.reporting.model.db.CustomerInfo;
import com.financialhouse.merchandise.reporting.model.db.Transaction;
import com.financialhouse.merchandise.reporting.model.rest.CustomerInfoJson;
import com.financialhouse.merchandise.reporting.model.rest.CustomerInfoQueryResponse;
import com.financialhouse.merchandise.reporting.model.rest.TransactionQueryResponse;

public final class ModelConverter {
    private ModelConverter() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }

    public static TransactionQueryResponse convertToQueryResponse(Transaction transaction){
        if (transaction == null) {
            return null;
        }

        return null;
    }

    public static CustomerInfoQueryResponse convert(CustomerInfo customerInfo) {
        if (customerInfo == null) {
            return null;
        }

        CustomerInfoQueryResponse ciqr = new CustomerInfoQueryResponse();

        CustomerInfoJson ci = new CustomerInfoJson();
        ci.setNumber(customerInfo.getCustomerNumber());
        ci.setCreatedAt(customerInfo.getCreatedAt());
        ci.setModifiedAt(customerInfo.getModifiedAt());
        ci.setDeletedAt(customerInfo.getDeletedAt());
        ci.setExpiryMonth(customerInfo.getExpiryMonth());
        ci.setExpiryYear(customerInfo.getExpiryYear());
        ci.setStartMonth(customerInfo.getStartMonth());
        ci.setStartYear(customerInfo.getStartYear());
        ci.setIssueNumber(customerInfo.getIssueNumber());
        ci.setEmail(customerInfo.getEmail());
        ci.setBirthday(customerInfo.getBirthday());
        ci.setGender(customerInfo.getGender());
        ci.setBillingTitle(customerInfo.getBillingTitle());
        ci.setBillingFirstName(customerInfo.getBillingFirstName());
        ci.setBillingLastName(customerInfo.getBillingLastName());
        ci.setBillingCompany(customerInfo.getBillingCompany());
        ci.setBillingAddress1(customerInfo.getBillingAddress1());
        ci.setBillingAddress2(customerInfo.getBillingAddress2());
        ci.setBillingCity(customerInfo.getBillingCity());
        ci.setBillingPostcode(customerInfo.getBillingPostcode());
        ci.setBillingState(customerInfo.getBillingState());
        ci.setBillingCountry(customerInfo.getBillingCountry());
        ci.setBillingPhone(customerInfo.getBillingPhone());
        ci.setBillingFax(customerInfo.getBillingFax());
        ci.setShippingTitle(customerInfo.getShippingTitle());
        ci.setShippingFirstName(customerInfo.getShippingFirstName());
        ci.setShippingLastName(customerInfo.getShippingLastName());
        ci.setShippingCompany(customerInfo.getShippingCompany());
        ci.setShippingAddress1(customerInfo.getShippingAddress1());
        ci.setShippingAddress2(customerInfo.getShippingAddress2());
        ci.setShippingCity(customerInfo.getShippingCity());
        ci.setShippingPostcode(customerInfo.getShippingPostcode());
        ci.setShippingState(customerInfo.getShippingState());
        ci.setShippingCountry(customerInfo.getShippingCountry());
        ci.setShippingPhone(customerInfo.getShippingPhone());
        ci.setShippingFax(customerInfo.getShippingFax());
        ci.setToken(customerInfo.getToken());

        ciqr.setCustomerInfo(ci);

        return ciqr;
    }
}
