package com.financialhouse.merchandise.reporting.controller;

import com.financialhouse.merchandise.reporting.config.JwtTokenUtil;
import com.financialhouse.merchandise.reporting.model.JwtRequest;
import com.financialhouse.merchandise.reporting.model.JwtResponse;
import com.financialhouse.merchandise.reporting.model.Status;
import com.financialhouse.merchandise.reporting.service.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class MerchantController {
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @RequestMapping(value = "/merchant/user/login", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest jwtRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(jwtRequest.getUsername());

        final String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(Status.APPROVED, token));
    }

    @GetMapping("/test")
    public ResponseEntity<?> test() {
        return ResponseEntity.ok("It is secured now!");
    }
}