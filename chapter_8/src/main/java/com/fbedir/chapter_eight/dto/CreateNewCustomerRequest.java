package com.fbedir.chapter_eight.dto;

import lombok.Data;

@Data
public class CreateNewCustomerRequest {

    private String name;

    private String surname;

    private String email;

    private String password;

}
