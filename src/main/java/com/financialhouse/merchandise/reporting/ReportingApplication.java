package com.financialhouse.merchandise.reporting;

import com.financialhouse.merchandise.reporting.model.ApiUser;
import com.financialhouse.merchandise.reporting.repository.ApiUserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

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
    public CommandLineRunner demo(ApiUserRepository repository) {
        return (args) -> {
            ApiUser user = new ApiUser();
            user.setUsername(username);
            user.setPassword(passwordEncoder().encode(password));
            repository.save(user);
        };
    }
}
