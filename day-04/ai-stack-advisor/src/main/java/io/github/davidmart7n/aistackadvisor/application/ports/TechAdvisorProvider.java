package io.github.davidmart7n.aistackadvisor.application.ports;

import io.github.davidmart7n.aistackadvisor.domain.Project;
import io.github.davidmart7n.aistackadvisor.domain.StackReport;

public interface TechAdvisorProvider {
    
    public StackReport getRecommendation(Project project);
}
