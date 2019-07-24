package com.financialhouse.merchandise.reporting;

import com.financialhouse.merchandise.reporting.model.db.ApiUser;
import com.financialhouse.merchandise.reporting.model.db.CustomerInfo;
import com.financialhouse.merchandise.reporting.model.db.Gender;
import com.financialhouse.merchandise.reporting.model.db.Transaction;
import com.financialhouse.merchandise.reporting.repository.ApiUserRepository;
import com.financialhouse.merchandise.reporting.repository.CustomerInfoRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class ReportingApplication {

    @Value("${apiuser.username}")
    private String username;

    @Value("${apiuser.password}")
    private String password;

    public static void main(String[] args) {
        SpringApplication.run(ReportingApplication.class, args);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CommandLineRunner demo(ApiUserRepository repository, CustomerInfoRepository customerInfoRepository) {
        return (args) -> {
            ApiUser user = new ApiUser();
            user.setUsername(username);
            user.setPassword(passwordEncoder().encode(password));
            repository.save(user);

            CustomerInfo ci = new CustomerInfo.Builder(4111111111111111L, (short) 1, (short) 2020, "seckin@bumin.io",
                    "Mr.", "SECKIN", "SEN", "BUMIN", "BUMN",
                    "ANTALYA", "07070", "ANTALYA", "TR", "05554443322")
                    .setStartMonth((short) 1)
                    .setStartYear((short) 2019)
                    .setIssueNumber(123456L)
                    .setBirthday(new Date())
                    .setGender(Gender.MALE)
                    .setBillingAddress2("SECK")
                    .setBillingFax("05554443322")
                    .setShippingTitle("Mr.")
                    .setShippingFirstName("SECKIN")
                    .setShippingLastName("SEN")
                    .setShippingCompany("BUMIN")
                    .setShippingAddress1("BUMN")
                    .setShippingAddress2("BUMN")
                    .setShippingCity("ANKARA")
                    .setShippingPostcode("06060")
                    .setShippingState("ANKARA")
                    .setShippingCountry("TR")
                    .setShippingPhone("05554443322")
                    .setShippingFax("05554443322")
                    .setToken("1t2o3k4e5n")
                    .build();

            Transaction transaction = new Transaction();
            transaction.setTransactionId("transactionId-1");
            transaction.setCustomerInfo(ci);
            List<Transaction> transactions = new ArrayList<>();
            transactions.add(transaction);

            ci.setTransactions(transactions);

            customerInfoRepository.save(ci);
        };
    }
}
