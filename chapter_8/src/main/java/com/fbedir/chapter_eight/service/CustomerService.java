package com.fbedir.chapter_eight.service;

import com.fbedir.chapter_eight.dto.CreateNewCustomerRequest;
import com.fbedir.chapter_eight.dto.CustomerResponse;

public interface CustomerService {

    void addNewCustomer(CreateNewCustomerRequest createNewCustomerRequest);

    CustomerResponse getCustomerById(Long id);

    CustomerResponse getCustomerByEmail(String email);

}
