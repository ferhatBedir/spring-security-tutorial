package com.fbedir.chapter_five.controller;

import com.fbedir.chapter_five.dto.Request;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class Controller {

    @GetMapping(path = "/")
    public ResponseEntity<Map<String, Object>> signIn() {
        return ResponseEntity.ok(Map.of("message", "Hello World"));
    }

    @PostMapping(path = "/csrf")
    public ResponseEntity<Map<String, Object>> signIn2(@RequestBody Request request) {
        return ResponseEntity.ok(Map.of("message", "csrf is enabled", "request", request.getUsername()));
    }

    @PostMapping(path = "/ignore-csrf")
    public ResponseEntity<Map<String, Object>> signIn3(@RequestBody Request request) {
        return ResponseEntity.ok(Map.of("message", "csrf is ignored", "request", request.getUsername()));
    }

}
