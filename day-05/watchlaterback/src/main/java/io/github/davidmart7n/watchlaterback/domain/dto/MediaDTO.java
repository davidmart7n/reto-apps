package io.github.davidmart7n.watchlaterback.domain.dto;

import io.github.davidmart7n.watchlaterback.domain.MediaType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Size;

public record MediaDTO(
    
    Long id,
    String title,
    MediaType type,
    double duration

){}
