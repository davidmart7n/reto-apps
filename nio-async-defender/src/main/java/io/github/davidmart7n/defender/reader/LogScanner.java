package io.github.davidmart7n.defender.reader;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

import io.github.davidmart7n.defender.analyzer.LogAnalyzer;
import io.github.davidmart7n.defender.config.AppConfig;
import io.github.davidmart7n.defender.model.LogEntry;

public class LogScanner {

    LogAnalyzer analyzer;

    public LogScanner() {
        analyzer = new LogAnalyzer();
    }

    public void fileReader(String fileName) {
        try {
            Stream<String> lines = Files.lines(Paths.get(fileName));

            lines.forEach((line) -> {

                AppConfig.getExecutor().submit(() -> {

                    System.out.println(Thread.currentThread().getName() + "procesando log: " + line);
                });
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
