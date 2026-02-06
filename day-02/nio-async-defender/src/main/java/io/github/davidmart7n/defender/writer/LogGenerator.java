package io.github.davidmart7n.defender.writer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.Reader;
import java.util.List;
import java.util.Random;

public class LogGenerator {

    private static final List<String> IPS = List.of(
            "192.168.1.1", "185.220.101.12", "45.33.2.145", "10.0.0.5",
            "103.212.43.1", "172.16.254.1", "203.0.113.42", "198.51.100.12",
            "8.8.8.8", "1.1.1.1", "91.241.19.84", "223.130.20.11",
            "109.236.81.14", "193.106.31.130", "5.188.62.144");

    private static final List<String> PATHS = List.of(
            "/index.html", "/login", "/api/users", "/contact",
            "/admin/config.php", "/wp-login.php", "/.env", "/etc/passwd",
            "/phpmyadmin", "/setup.cgi", "/backup.sql", "/config.json",
            "/api/v1/debug", "/server-status", "/console", "/shell.jsp");

    private static final List<String> AGENTS = List.of(
            "Mozilla/5.0 (Windows NT 10.0; Win64; x64)", "AppleWebKit/537.36 (Chrome/121.0.0.0)",
            "SqlMap/1.4", "ZGrab/2.0", "Python-requests/2.25", "Nmap Scripting Engine",
            "Go-http-client/1.1", "curl/7.68.0", "Wget/1.20", "PostmanRuntime/7.29.0",
            "Baiduspider", "Googlebot/2.1", "Bingbot/2.0");

    private static final List<Integer> STATUS_CODES = List.of(200, 201, 400, 401, 403, 404, 500);

    public static void generator(String fileName, int lines) {

        Random random = new Random();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (int i = 0; i < lines; i++) {
                int ipsRandom = random.nextInt(IPS.size());
                int pathsRandom = random.nextInt(PATHS.size());
                int agentsRandom = random.nextInt(AGENTS.size());
                int statusRandom = random.nextInt(STATUS_CODES.size());

                StringBuilder stringBuilder = new StringBuilder();

                stringBuilder.append(IPS.get(ipsRandom)).append(",");
                stringBuilder.append(PATHS.get(pathsRandom)).append(",");
                stringBuilder.append(AGENTS.get(agentsRandom)).append(",");
                stringBuilder.append(STATUS_CODES.get(statusRandom));

                writer.write(stringBuilder.toString());
                writer.newLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
