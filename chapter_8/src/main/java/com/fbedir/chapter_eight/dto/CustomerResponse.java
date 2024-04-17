package com.fbedir.chapter_eight.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerResponse {

    private Long id;

    private String name;

    private String surname;

    private String email;

    private String password;

}
