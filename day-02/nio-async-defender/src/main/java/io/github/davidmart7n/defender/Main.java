package io.github.davidmart7n.defender;

import java.util.concurrent.TimeUnit;

import io.github.davidmart7n.defender.analyzer.LogAnalyzer;
import io.github.davidmart7n.defender.config.AppConfig;
import io.github.davidmart7n.defender.reader.LogScanner;
import io.github.davidmart7n.defender.writer.LogGenerator;

public class Main {
    public static void main(String[] args) {
        String logFile = "logEntries.log";
        LogAnalyzer analyzer = new LogAnalyzer();
        LogScanner scanner = new LogScanner(analyzer);

        LogGenerator.generator(logFile, 1000);

        scanner.fileReader(logFile);

        AppConfig.getExecutor().shutdown();
        try {
            if (!AppConfig.getExecutor().awaitTermination(60000, TimeUnit.SECONDS)) {
                AppConfig.getExecutor().shutdownNow();
            };
        } catch (InterruptedException e) {
            AppConfig.getExecutor().shutdownNow();
        }
        analyzer.finalizeAnalysis("final_report.txt");
    }
}