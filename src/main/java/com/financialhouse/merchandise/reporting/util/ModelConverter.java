package com.financialhouse.merchandise.reporting.util;

import com.financialhouse.merchandise.reporting.model.db.CustomerInfo;
import com.financialhouse.merchandise.reporting.model.rest.CustomerInfoQueryResponse;

public class ModelConverter {
    private ModelConverter() {
    }

    public static CustomerInfoQueryResponse convert(CustomerInfo customerInfo) {
        if (customerInfo == null) {
            return null;
        }

        CustomerInfoQueryResponse ciqr = new CustomerInfoQueryResponse();

        ciqr.setNumber(customerInfo.getNumber());
        ciqr.setCreatedAt(customerInfo.getCreatedAt());
        ciqr.setModifiedAt(customerInfo.getModifiedAt());
        ciqr.setDeletedAt(customerInfo.getDeletedAt());
        ciqr.setExpiryMonth(customerInfo.getExpiryMonth());
        ciqr.setExpiryYear(customerInfo.getExpiryYear());
        ciqr.setStartMonth(customerInfo.getStartMonth());
        ciqr.setStartYear(customerInfo.getStartYear());
        ciqr.setIssueNumber(customerInfo.getIssueNumber());
        ciqr.setEmail(customerInfo.getEmail());
        ciqr.setBirthday(customerInfo.getBirthday());
        ciqr.setGender(customerInfo.getGender());
        ciqr.setBillingTitle(customerInfo.getBillingTitle());
        ciqr.setBillingFirstName(customerInfo.getBillingFirstName());
        ciqr.setBillingLastName(customerInfo.getBillingLastName());
        ciqr.setBillingCompany(customerInfo.getBillingCompany());
        ciqr.setBillingAddress1(customerInfo.getBillingAddress1());
        ciqr.setBillingAddress2(customerInfo.getBillingAddress2());
        ciqr.setBillingCity(customerInfo.getBillingCity());
        ciqr.setBillingPostcode(customerInfo.getBillingPostcode());
        ciqr.setBillingState(customerInfo.getBillingState());
        ciqr.setBillingCountry(customerInfo.getBillingCountry());
        ciqr.setBillingPhone(customerInfo.getBillingPhone());
        ciqr.setBillingFax(customerInfo.getBillingFax());
        ciqr.setShippingTitle(customerInfo.getShippingTitle());
        ciqr.setShippingFirstName(customerInfo.getShippingFirstName());
        ciqr.setShippingLastName(customerInfo.getShippingLastName());
        ciqr.setShippingCompany(customerInfo.getShippingCompany());
        ciqr.setShippingAddress1(customerInfo.getShippingAddress1());
        ciqr.setShippingAddress2(customerInfo.getShippingAddress2());
        ciqr.setShippingCity(customerInfo.getShippingCity());
        ciqr.setShippingPostcode(customerInfo.getShippingPostcode());
        ciqr.setShippingState(customerInfo.getShippingState());
        ciqr.setShippingCountry(customerInfo.getShippingCountry());
        ciqr.setShippingPhone(customerInfo.getShippingPhone());
        ciqr.setShippingFax(customerInfo.getShippingFax());
        ciqr.setToken(customerInfo.getToken());

        return ciqr;
    }
}
