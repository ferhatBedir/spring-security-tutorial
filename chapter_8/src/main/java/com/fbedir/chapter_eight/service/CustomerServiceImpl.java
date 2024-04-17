package com.fbedir.chapter_eight.service;

import com.fbedir.chapter_eight.dto.CreateNewCustomerRequest;
import com.fbedir.chapter_eight.dto.CustomerResponse;
import com.fbedir.chapter_eight.entity.CustomerEntity;
import com.fbedir.chapter_eight.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public void addNewCustomer(CreateNewCustomerRequest createNewCustomerRequest) {
        String encodedPassword = passwordEncoder.encode(createNewCustomerRequest.getPassword());
        CustomerEntity customerEntity = CustomerEntity.builder()
                                                      .createdAt(LocalDateTime.now())
                                                      .name(createNewCustomerRequest.getName())
                                                      .surname(createNewCustomerRequest.getSurname())
                                                      .email(createNewCustomerRequest.getEmail())
                                                      .password(encodedPassword)
                                                      .build();
        customerRepository.save(customerEntity);

    }

    @Override
    public CustomerResponse getCustomerById(Long id) {
        CustomerEntity customerEntity = customerRepository.findById(id).orElseThrow(() -> new RuntimeException("Customer not found with id: " + id));
        return CustomerResponse.builder()
                               .id(customerEntity.getId())
                               .name(customerEntity.getName())
                               .surname(customerEntity.getSurname())
                               .email(customerEntity.getEmail())
                               .password(customerEntity.getPassword())
                               .build();

    }

    @Override
    public CustomerResponse getCustomerByEmail(String email) {
        CustomerEntity customerEntity = customerRepository.findByEmail(email)
                                                          .orElseThrow(() -> new RuntimeException("Customer not found with email: " + email));
        return CustomerResponse.builder()
                               .id(customerEntity.getId())
                               .name(customerEntity.getName())
                               .surname(customerEntity.getSurname())
                               .email(customerEntity.getEmail())
                               .password(customerEntity.getPassword())
                               .build();

    }

}
