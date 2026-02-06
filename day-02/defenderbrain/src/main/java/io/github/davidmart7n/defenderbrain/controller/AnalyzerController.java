package io.github.davidmart7n.defenderbrain.controller;

import org.springframework.web.bind.annotation.*;

import io.github.davidmart7n.defenderbrain.dto.LogEntryDTO;

import java.util.Set;

@RestController
@RequestMapping("/api")
public class AnalyzerController {

    // Simulamos una base de datos de IPs bloqueadas
    private final Set<String> blacklistedIps = Set.of("185.220.101.12", "91.241.19.84");

    @PostMapping("/security-analyze")
    public String analyzeLog(@RequestBody LogEntryDTO entry) {
        System.out.println("Checking IP: " + entry.ip());

        // Lógica simple: si está en la lista negra, ordena bloquear
        if (blacklistedIps.contains(entry.ip())) {
            return "BLOCK";
        }

        // Si el agente es sospechoso, también podemos bloquear desde aquí
        if (entry.agent().contains("SqlMap")) {
            return "BLOCK";
        }

        return "OK";
    }
}