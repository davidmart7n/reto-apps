package io.github.davidmart7n.defender.reader;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

import io.github.davidmart7n.defender.analyzer.LogAnalyzer;
import io.github.davidmart7n.defender.model.LogEntry;

public class LogScanner {
    
    LogAnalyzer analyzer;

    public LogScanner(){
        analyzer=new LogAnalyzer();
    }

    public static void fileReader(List<LogEntry> logsList, String fileName) throws Exception{

    Stream<String> lines=Files.lines(Paths.get(fileName));

    lines.forEach((l)->{  });
    
        
    }
}
