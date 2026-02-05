package io.github.davidmart7n.defenderbrain.dto;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonProperty;

public record LogEntryDTO(
    String ip, 
    String path,
    String agent,
    int statusCode
) {}