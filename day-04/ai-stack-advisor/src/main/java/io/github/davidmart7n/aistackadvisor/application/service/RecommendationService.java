package io.github.davidmart7n.aistackadvisor.application.service;

import org.springframework.stereotype.Service;

import io.github.davidmart7n.aistackadvisor.application.ports.TechAdvisorProvider;
import io.github.davidmart7n.aistackadvisor.domain.Project;
import io.github.davidmart7n.aistackadvisor.domain.StackReport;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RecommendationService {
    
    private final TechAdvisorProvider geminiAdvisor;

    public StackReport recommendationRequest(Project project){

            return geminiAdvisor.getRecommendation(project);
}
}
