package com.bookStore.bookStore.controller;

import com.bookStore.bookStore.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*")
public class ValidationController {

    private final UserRepository userRepository;

    public ValidationController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/check/username")
    public ResponseEntity<Map<String, Object>> checkUsername(@RequestParam String username) {
        boolean exists = userRepository.existsByUsername(username);
        Map<String, Object> response = new HashMap<>();
        response.put("available", !exists);
        response.put("message", exists ? "El nombre de usuario ya está en uso" : "Disponible");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/check/email")
    public ResponseEntity<Map<String, Object>> checkEmail(@RequestParam String email) {
        boolean exists = userRepository.existsByEmail(email);
        Map<String, Object> response = new HashMap<>();
        response.put("available", !exists);
        response.put("message", exists ? "El correo electrónico ya está registrado" : "Disponible");
        return ResponseEntity.ok(response);
    }
}