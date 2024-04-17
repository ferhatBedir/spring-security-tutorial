package com.fbedir.chapter_three.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class Controller {

    private final PasswordEncoder passwordEncoder;

    @GetMapping(path = "/")
    public ResponseEntity<Map<String, Object>> signIn() {
        String encode = passwordEncoder.encode("123456");
        System.out.println("encode = " + encode);
        return ResponseEntity.ok(Map.of("message", "Hello World"));
    }

}
