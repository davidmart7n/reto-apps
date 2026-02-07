package io.github.davidmart7n.aistackadvisor.domain;

public record StackReport(
    String backend,
    String frontend,
    String database,
    String reasoning,
    String estimatedCost,
    String estimatedTime
) {}