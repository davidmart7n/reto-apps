package io.github.davidmart7n.defender;

import io.github.davidmart7n.defender.analyzer.LogAnalyzer;
import io.github.davidmart7n.defender.reader.LogScanner;
import io.github.davidmart7n.defender.writer.LogGenerator;

public class Main {
    public static void main(String[] args) {
        String logFile = "logs.txt";
        LogAnalyzer analyzer = new LogAnalyzer();
        LogScanner scanner = new LogScanner();

        // 1. Generamos los logs
        LogGenerator.generator(logFile, 1000);

        // 2. Escaneamos (este método debe esperar a que termine el archivo)
        scanner.fileReader(logFile);

        // 3. UNA VEZ TERMINA EL ESCANEO, llamamos al reporte
        analyzer.finalizeAnalysis("final_report.txt");
    }
}