package io.github.davidmart7n.aistackadvisor.infrastructure.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.davidmart7n.aistackadvisor.application.service.RecommendationService;
import io.github.davidmart7n.aistackadvisor.domain.Project;
import io.github.davidmart7n.aistackadvisor.domain.StackReport;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/ai")
@CrossOrigin(origins="*")
public class StackAdvisorController {
    
    private final RecommendationService service;

    @GetMapping
    public String healthy() {
        return "api working!";
    }

    @PostMapping()
    public ResponseEntity<StackReport> getAiRecommendation(@RequestBody Project project) {
       StackReport report= this.service.recommendationRequest(project);
        return  ResponseEntity.ok().body(report);
    }
    
}
