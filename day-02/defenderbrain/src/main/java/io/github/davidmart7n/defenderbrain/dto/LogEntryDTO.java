package io.github.davidmart7n.defenderbrain.dto;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;

public record LogEntryDTO(
    @NotBlank 
    String ip, 

    @NotBlank 
    String path,

    @NotBlank 
    String agent,

    @Min(100) @Max(599) 
    int statusCode

) {}