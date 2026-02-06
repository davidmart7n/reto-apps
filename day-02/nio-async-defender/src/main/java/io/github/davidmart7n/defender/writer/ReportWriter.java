package io.github.davidmart7n.defender.writer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class ReportWriter {

    public static void saveReport(String fileName, int totalLines, int threats, Map<String, Integer> attacksMap) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write("=== DEFENDER SECURITY REPORT ===\n");
            writer.write("Total lines processed: " + totalLines + "\n");
            writer.write("Threats detected: " + threats + "\n");
            writer.write("------------------------------------\n");
            writer.write("Top Attacking IPs:\n");

            for (Map.Entry<String, Integer> entry : attacksMap.entrySet()) {
                writer.write("IP: " + entry.getKey() + " - Attempts: " + entry.getValue() + "\n");
            }

            System.out.println("📄 Report successfully saved in: " + fileName);
        } catch (IOException e) {
            System.err.println("❌ Error writing report: " + e.getMessage());
        }
    }
}