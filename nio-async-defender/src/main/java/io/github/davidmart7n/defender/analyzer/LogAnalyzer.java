package io.github.davidmart7n.defender.analyzer;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.davidmart7n.defender.model.LogEntry;

public class LogAnalyzer {

    private static final List<String> DANGEROUS_PATHS = List.of("/admin", "/.env", "/etc/passwd", "/phpmyadmin",
            "/setup.cgi", "/shell.jsp");

    private static final List<String> DANGEROUS_AGENTS = List.of("SqlMap", "Nmap", "ZGrab", "Go-http-client");

    public void securityAnalisis(String line) {

        LogEntry entry = parseLine(line);

        if (isSuspicious(entry)) {
            System.out.println("🚨 ATAQUE DETECTADO: " + entry.ip());
            try {
                ObjectMapper mapper = new ObjectMapper();
                String entryJson = mapper.writeValueAsString(entry);
                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create("null"))
                        .POST(BodyPublishers.ofString(entryJson))
                        .build();
                HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

                handleAction(response.body(), entry.ip());

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private boolean isSuspicious(LogEntry entry) {

        boolean badPath = DANGEROUS_PATHS.stream().anyMatch(entry.path()::contains) && entry.statusCode() >= 400;
        boolean badAgent = DANGEROUS_AGENTS.stream().anyMatch(entry.agent()::contains);

        return badPath || badAgent;
    }

    private LogEntry parseLine(String line) {
        String[] parts = line.split(",");
        return new LogEntry(parts[0], parts[1], parts[2], Integer.parseInt(parts[3]));
    }

    private void handleAction(String action, String ip) {
        switch (action) {
            case "BLOCK" -> System.out.println("⛔ [FIREWALL] Bloqueando IP: " + ip);
            case "WATCH" -> System.out.println("👀 [MONITOR] IP " + ip + " bajo vigilancia estricta.");
            default -> System.out.println("✅ [INFO] Sin acciones requeridas para: " + ip);
        }
    }

}
