package io.github.davidmart7n.multifactorbankguard.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import io.github.davidmart7n.multifactorbankguard.model.BankUser;
import io.github.davidmart7n.multifactorbankguard.model.dto.RegisterRequest;
import io.github.davidmart7n.multifactorbankguard.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public BankUser register(RegisterRequest registerDto) {
        BankUser user = new BankUser();
        user.setName(registerDto.getName());
        user.setSecondName(registerDto.getSecondName());
        user.setUsername(registerDto.getUsername());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        user.setSecretPin(passwordEncoder.encode(registerDto.getSecretPin()));

        return userRepository.save(user);
    }

    public boolean verifyPassword(String username, String rawPassword) {
        return userRepository.findByUsername(username)
                .map(user -> passwordEncoder.matches(rawPassword, user.getPassword()))
                .orElse(false);
    }

    public boolean verifyPin(String username, String rawPin) {
        return userRepository.findByUsername(username)
                .map(user -> passwordEncoder.matches(rawPin, user.getSecretPin()))
                .orElse(false);
    }

}
