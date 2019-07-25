package com.financialhouse.merchandise.reporting.model.db;

import com.financialhouse.merchandise.reporting.model.db.enums.Gender;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "CUSTOMER_INFO")
@NoArgsConstructor
public class CustomerInfo {
    @OneToMany(mappedBy = "customerInfo", cascade = CascadeType.ALL)
    List<Transaction> transactions;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Setter(AccessLevel.NONE)
    private Date createdAt;
    @Setter(AccessLevel.NONE)
    private Date modifiedAt;
    @Setter(AccessLevel.NONE)
    private Date deletedAt;
    private Long number;
    private Short expiryMonth;
    private Short expiryYear;
    private Short startMonth;
    private Short startYear;
    private Long issueNumber;
    private String email;
    private Date birthday;
    private Gender gender;
    private String billingTitle;
    private String billingFirstName;
    private String billingLastName;
    private String billingCompany;
    private String billingAddress1;
    private String billingAddress2;
    private String billingCity;
    private String billingPostcode;
    private String billingState;
    private String billingCountry;
    private String billingPhone;
    private String billingFax;
    private String shippingTitle;
    private String shippingFirstName;
    private String shippingLastName;
    private String shippingCompany;
    private String shippingAddress1;
    private String shippingAddress2;
    private String shippingCity;
    private String shippingPostcode;
    private String shippingState;
    private String shippingCountry;
    private String shippingPhone;
    private String shippingFax;
    private String token;

    public CustomerInfo(Builder builder) {
        this.number = builder.number;
        this.expiryMonth = builder.expiryMonth;
        this.expiryYear = builder.expiryYear;
        this.startMonth = builder.startMonth;
        this.startYear = builder.startYear;
        this.issueNumber = builder.issueNumber;
        this.email = builder.email;
        this.birthday = builder.birthday;
        this.gender = builder.gender;
        this.billingTitle = builder.billingTitle;
        this.billingFirstName = builder.billingFirstName;
        this.billingLastName = builder.billingLastName;
        this.billingCompany = builder.billingCompany;
        this.billingAddress1 = builder.billingAddress1;
        this.billingAddress2 = builder.billingAddress2;
        this.billingCity = builder.billingCity;
        this.billingPostcode = builder.billingPostcode;
        this.billingState = builder.billingState;
        this.billingCountry = builder.billingCountry;
        this.billingPhone = builder.billingPhone;
        this.billingFax = builder.billingFax;
        this.shippingTitle = builder.shippingTitle;
        this.shippingFirstName = builder.shippingFirstName;
        this.shippingLastName = builder.shippingLastName;
        this.shippingCompany = builder.shippingCompany;
        this.shippingAddress1 = builder.shippingAddress1;
        this.shippingAddress2 = builder.shippingAddress2;
        this.shippingCity = builder.shippingCity;
        this.shippingPostcode = builder.shippingPostcode;
        this.shippingState = builder.shippingState;
        this.shippingCountry = builder.shippingCountry;
        this.shippingPhone = builder.shippingPhone;
        this.shippingFax = builder.shippingFax;
        this.token = builder.token;
        this.transactions = builder.transactions;
    }

    @PrePersist
    private void prePersist() {
        Date now = new Date();
        this.createdAt = now;
        this.modifiedAt = now;
    }

    @PreUpdate
    private void preUpdate() {
        this.modifiedAt = new Date();
    }

    public static class Builder {
        private final Long number;
        private final Short expiryMonth;
        private final Short expiryYear;
        private final String email;
        private final String billingTitle;
        private final String billingFirstName;
        private final String billingLastName;
        private final String billingCompany;
        private final String billingAddress1;
        private final String billingCity;
        private final String billingPostcode;
        private final String billingState;
        private final String billingCountry;
        private final String billingPhone;

        private Short startMonth;
        private Short startYear;
        private Long issueNumber;
        private Date birthday;
        private Gender gender;
        private String billingAddress2;
        private String billingFax;
        private String shippingTitle;
        private String shippingFirstName;
        private String shippingLastName;
        private String shippingCompany;
        private String shippingAddress1;
        private String shippingAddress2;
        private String shippingCity;
        private String shippingPostcode;
        private String shippingState;
        private String shippingCountry;
        private String shippingPhone;
        private String shippingFax;
        private String token;
        private List<Transaction> transactions;

        public Builder(Long number, Short expiryMonth, Short expiryYear,
                       String email, String billingTitle, String billingFirstName,
                       String billingLastName, String billingCompany, String billingAddress1,
                       String billingCity, String billingPostcode, String billingState,
                       String billingCountry, String billingPhone) {
            this.number = number;
            this.expiryMonth = expiryMonth;
            this.expiryYear = expiryYear;
            this.email = email;
            this.billingTitle = billingTitle;
            this.billingFirstName = billingFirstName;
            this.billingLastName = billingLastName;
            this.billingCompany = billingCompany;
            this.billingAddress1 = billingAddress1;
            this.billingCity = billingCity;
            this.billingPostcode = billingPostcode;
            this.billingState = billingState;
            this.billingCountry = billingCountry;
            this.billingPhone = billingPhone;
        }

        public Builder setStartMonth(Short startMonth) {
            this.startMonth = startMonth;
            return this;
        }

        public Builder setStartYear(Short startYear) {
            this.startYear = startYear;
            return this;
        }

        public Builder setIssueNumber(Long issueNumber) {
            this.issueNumber = issueNumber;
            return this;
        }

        public Builder setBirthday(final Date birthday) {
            this.birthday = birthday;
            return this;
        }

        public Builder setGender(Gender gender) {
            this.gender = gender;
            return this;
        }

        public Builder setBillingAddress2(String billingAddress2) {
            this.billingAddress2 = billingAddress2;
            return this;
        }

        public Builder setBillingFax(String billingFax) {
            this.billingFax = billingFax;
            return this;
        }

        public Builder setShippingTitle(String shippingTitle) {
            this.shippingTitle = shippingTitle;
            return this;
        }

        public Builder setShippingFirstName(String shippingFirstName) {
            this.shippingFirstName = shippingFirstName;
            return this;
        }

        public Builder setShippingLastName(String shippingLastName) {
            this.shippingLastName = shippingLastName;
            return this;
        }

        public Builder setShippingCompany(String shippingCompany) {
            this.shippingCompany = shippingCompany;
            return this;
        }

        public Builder setShippingAddress1(String shippingAddress1) {
            this.shippingAddress1 = shippingAddress1;
            return this;
        }

        public Builder setShippingAddress2(String shippingAddress2) {
            this.shippingAddress2 = shippingAddress2;
            return this;
        }

        public Builder setShippingCity(String shippingCity) {
            this.shippingCity = shippingCity;
            return this;
        }

        public Builder setShippingPostcode(String shippingPostcode) {
            this.shippingPostcode = shippingPostcode;
            return this;
        }

        public Builder setShippingState(String shippingState) {
            this.shippingState = shippingState;
            return this;
        }

        public Builder setShippingCountry(String shippingCountry) {
            this.shippingCountry = shippingCountry;
            return this;
        }

        public Builder setShippingPhone(String shippingPhone) {
            this.shippingPhone = shippingPhone;
            return this;
        }

        public Builder setShippingFax(String shippingFax) {
            this.shippingFax = shippingFax;
            return this;
        }

        public Builder setToken(String token) {
            this.token = token;
            return this;
        }

        public Builder setTransactions(List<Transaction> transactions) {
            this.transactions = transactions;
            return this;
        }

        public CustomerInfo build() {
            return new CustomerInfo(this);
        }
    }
}
