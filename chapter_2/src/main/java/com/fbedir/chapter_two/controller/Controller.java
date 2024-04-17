package com.fbedir.chapter_two.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class Controller {

    @GetMapping(path = "/")
    public ResponseEntity<Map<String, Object>> signIn() {
        return ResponseEntity.ok(Map.of("message", "Hello World"));
    }

}
