package com.financialhouse.merchandise.reporting;

import com.financialhouse.merchandise.reporting.model.db.*;
import com.financialhouse.merchandise.reporting.model.db.enums.AcquirerType;
import com.financialhouse.merchandise.reporting.model.db.enums.Gender;
import com.financialhouse.merchandise.reporting.model.db.enums.Status;
import com.financialhouse.merchandise.reporting.repository.ApiUserRepository;
import com.financialhouse.merchandise.reporting.repository.CustomerInfoRepository;
import com.financialhouse.merchandise.reporting.repository.MerchantRepository;
import com.financialhouse.merchandise.reporting.repository.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.text.WordUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Slf4j
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
    public CommandLineRunner demo(ApiUserRepository repository,
                                  CustomerInfoRepository customerInfoRepository,
                                  MerchantRepository merchantRepository,
                                  TransactionRepository transactionRepository) {
        return (args) -> {
            ApiUser user = new ApiUser();
            user.setUsername(username);
            user.setPassword(passwordEncoder().encode(password));
            ApiUser savedApiUser = repository.save(user);

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
            CustomerInfo savedCi = customerInfoRepository.save(ci);

            Merchant merchant = Merchant.builder().name("Seckin Merchant")
                    .allowedPartialRefund(true)
                    .allowPartialCapture(true)
                    .build();
            Merchant savedMerchant = merchantRepository.save(merchant);

            AcquirerTransaction acquirerTransaction1 = AcquirerTransaction.builder().name("RoyalPay PayToCard")
                    .code("RP")
                    .type(AcquirerType.PAYTOCARD)
                    .acquirerTransactionId(UUID.randomUUID().toString())
                    .build();

            Agent agent1 = Agent.builder().customerIp("213.74.172.226")
                    .customerUserAgent("PostmanRuntime/7.3.0")
                    .customerIp("213.74.172.226")
                    .merchantUserAgent("PostmanRuntime/7.3.0").build();

            FxTransaction fxTransaction1 = FxTransaction.builder().convertedAmount(new BigDecimal(1500))
                    .originalCurrency("RUB")
                    .convertedAmount(new BigDecimal(1500))
                    .convertedCurrency("RUB")
                    .fxTransactionId(UUID.randomUUID().toString())
                    .build();

            IPN ipn1 = IPN.builder().sent(true)
                    .token(UUID.randomUUID().toString())
                    .type("MERCHANTIPN")
                    .url("https://requestb.in/10bmd651")
                    .build();

            Transaction transaction1 = new Transaction();
            transaction1.setCustomerInfo(savedCi);
            transaction1.setMerchant(savedMerchant);
            transaction1.setAcquirerTransaction(acquirerTransaction1);
            transaction1.setAgent(agent1);
            transaction1.setFxTransaction(fxTransaction1);
            transaction1.setIpn(ipn1);

            acquirerTransaction1.setTransaction(transaction1);
            agent1.setTransaction(transaction1);
            fxTransaction1.setTransaction(transaction1);
            ipn1.setTransaction(transaction1);

            transaction1.setReferenceNo("trn-test-seck-1");
            transaction1.setChainId(UUID.randomUUID().toString());
            transaction1.setReturnUrl(null);
            transaction1.setStatus(Status.APPROVED);
            transaction1.setCode("00");
            transaction1.setMessage(WordUtils.capitalizeFully(Status.APPROVED.name()));
            transaction1.setChannel("API");
            transaction1.setType("AUTH");

            Transaction savedTransaction = transactionRepository.save(transaction1);
            log.debug("transactionId: " + savedTransaction.getTransactionId());

            AcquirerTransaction acquirerTransaction2 = AcquirerTransaction.builder().name("RoyalPay PayToCard")
                    .code("RP")
                    .type(AcquirerType.PAYTOCARD)
                    .acquirerTransactionId(UUID.randomUUID().toString())
                    .build();

            Agent agent2 = Agent.builder().customerIp("213.74.172.226")
                    .customerUserAgent("PostmanRuntime/7.3.0")
                    .customerIp("213.74.172.226")
                    .merchantUserAgent("PostmanRuntime/7.3.0").build();

            FxTransaction fxTransaction2 = FxTransaction.builder().convertedAmount(new BigDecimal(1500))
                    .originalCurrency("RUB")
                    .convertedAmount(new BigDecimal(1500))
                    .convertedCurrency("RUB")
                    .fxTransactionId(UUID.randomUUID().toString())
                    .build();

            IPN ipn2 = IPN.builder().sent(true)
                    .token(UUID.randomUUID().toString())
                    .type("MERCHANTIPN")
                    .url("https://requestb.in/10bmd651")
                    .build();

            Transaction transaction2 = new Transaction();
            transaction2.setCustomerInfo(savedCi);
            transaction2.setMerchant(savedMerchant);
            transaction2.setAcquirerTransaction(acquirerTransaction2);
            transaction2.setAgent(agent2);
            transaction2.setFxTransaction(fxTransaction2);
            transaction2.setIpn(ipn2);

            acquirerTransaction2.setTransaction(transaction2);
            agent2.setTransaction(transaction2);
            fxTransaction2.setTransaction(transaction2);
            ipn2.setTransaction(transaction2);

            transaction2.setReferenceNo("trn-test-seck-1");
            transaction2.setChainId(UUID.randomUUID().toString());
            transaction2.setReturnUrl(null);
            transaction2.setStatus(Status.APPROVED);
            transaction2.setCode("00");
            transaction2.setMessage(WordUtils.capitalizeFully(Status.APPROVED.name()));
            transaction2.setChannel("API");
            transaction2.setType("AUTH");

            Transaction savedTransaction2 = transactionRepository.save(transaction2);
            log.debug("transactionId: " + savedTransaction2.getTransactionId());

        };
    }
}
