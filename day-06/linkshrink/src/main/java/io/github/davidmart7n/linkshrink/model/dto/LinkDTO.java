package io.github.davidmart7n.linkshrink.model.dto;

public record LinkDTO(
    Long id,
    String shortCode,
    String shortUrl, 
    String originalUrl,
    Long clicks
) {}
