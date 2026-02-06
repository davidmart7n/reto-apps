package io.github.davidmart7n.defender.model;

import java.time.Instant;

public record LogEntry (
    String ip, 
    String path, 
    String agent,    
    int statusCode
) {}