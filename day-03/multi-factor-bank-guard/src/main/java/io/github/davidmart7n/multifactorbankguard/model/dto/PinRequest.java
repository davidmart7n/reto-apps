package io.github.davidmart7n.multifactorbankguard.model.dto;

import lombok.Data;

@Data
public class PinRequest {
    private String username;
    private String secretPin;
}
