package com.fbedir.chapter_eight.controller;

import com.fbedir.chapter_eight.dto.CreateNewCustomerRequest;
import com.fbedir.chapter_eight.dto.CustomerResponse;
import com.fbedir.chapter_eight.dto.SignInRequest;
import com.fbedir.chapter_eight.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class Controller {

    private final CustomerService service;

    @PreAuthorize("#createRequest.name != 'test'")
    @PostMapping(path = "/sign-up")
    public ResponseEntity<Void> signUp(@RequestBody CreateNewCustomerRequest createRequest) {
        service.addNewCustomer(createRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }

    @PostMapping(path = "/sign-in")
    public ResponseEntity<CustomerResponse> signIn(@RequestBody SignInRequest signInRequest) {
        CustomerResponse customerResponse = service.getCustomerByEmail(signInRequest.getEmail());
        return ResponseEntity.ok(customerResponse);

    }

    @GetMapping(path = "/get-customer/{id}")
    public ResponseEntity<CustomerResponse> getCustomer(@PathVariable("id") Long id) {
        CustomerResponse customerResponse = service.getCustomerById(id);
        return ResponseEntity.ok(customerResponse);

    }

    @GetMapping(path = "/public")
    public ResponseEntity<Map<String, Object>> publicEndpoint() {
        return ResponseEntity.ok(Map.of("message", "Hello World"));

    }

}
