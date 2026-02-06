package io.github.davidmart7n.multifactorbankguard.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.davidmart7n.multifactorbankguard.model.BankUser;
import io.github.davidmart7n.multifactorbankguard.model.dto.LoginRequest;
import io.github.davidmart7n.multifactorbankguard.model.dto.PinRequest;
import io.github.davidmart7n.multifactorbankguard.model.dto.RegisterRequest;
import io.github.davidmart7n.multifactorbankguard.repository.UserRepository;
import io.github.davidmart7n.multifactorbankguard.security.JwtUtils;
import io.github.davidmart7n.multifactorbankguard.service.AuthService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class AuthController {
    
    private final AuthService authService;
    private final JwtUtils jwtUtils;

    @PostMapping("/register")
    public ResponseEntity<BankUser> register(@RequestBody RegisterRequest request) {
        return new ResponseEntity<>(authService.register(request), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        boolean isValid = authService.verifyPassword(request.getUsername(), request.getPassword());
        
        if (isValid) {
            return ResponseEntity.ok(Map.of("message", "Password OK. Please provide PIN."));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
    }

    @PostMapping("/verify-pin")
    public ResponseEntity<?> verifyPin(@RequestBody PinRequest request) {
        boolean isValid = authService.verifyPin(request.getUsername(), request.getPin());

        if (isValid) {
            String token = jwtUtils.generateToken(request.getUsername());
            return ResponseEntity.ok(Map.of("token", token));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid PIN");
    }
}


