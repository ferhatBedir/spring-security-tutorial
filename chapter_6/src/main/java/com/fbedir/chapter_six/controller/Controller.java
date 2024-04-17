package com.fbedir.chapter_six.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class Controller {

    @GetMapping(path = "/for-admin-role")
    public ResponseEntity<Map<String, Object>> roleAdmin() {
        return ResponseEntity.ok(Map.of("message", "I am a admin.."));
    }

    @GetMapping(path = "/for-user-role")
    public ResponseEntity<Map<String, Object>> roleUser() {
        return ResponseEntity.ok(Map.of("message", "I am a user.."));
    }

    @GetMapping(path = "/for-all-role")
    public ResponseEntity<Map<String, Object>> roleAdminAndUser() {
        return ResponseEntity.ok(Map.of("message", "I am a admin and user.."));
    }

    @GetMapping(path = "/for-view-authorities")
    public ResponseEntity<Map<String, Object>> viewAuthorities() {
        return ResponseEntity.ok(Map.of("message", "I have view authority.."));
    }

    @GetMapping(path = "/for-write-authorities")
    public ResponseEntity<Map<String, Object>> writeAuthorities() {
        return ResponseEntity.ok(Map.of("message", "I have write authority.."));
    }

    @GetMapping(path = "/for-all-authorities")
    public ResponseEntity<Map<String, Object>> writeAndViewAuthorities() {
        return ResponseEntity.ok(Map.of("message", "I have write and view authority.."));
    }

}
