package io.github.davidmart7n.aistackadvisor.infrastructure.ai;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.converter.BeanOutputConverter;
import org.springframework.stereotype.Service;

import io.github.davidmart7n.aistackadvisor.application.ports.TechAdvisorProvider;
import io.github.davidmart7n.aistackadvisor.domain.Project;
import io.github.davidmart7n.aistackadvisor.domain.StackReport;

@Service
public class GeminiTechAdvisor implements TechAdvisorProvider {

    private final ChatClient chatClient;

    public GeminiTechAdvisor(ChatClient.Builder builder){
        this.chatClient= builder.build();
    }

    public StackReport getRecommendation(Project project) {
        var converter = new BeanOutputConverter<>(StackReport.class);

        String promptContext = String.format(
                "Project Name: %s, Type: %s, Team Skills: %s, Requirements: %s",
                project.projectName(), project.projectType(),
                project.teamSkills(), project.mainRequirements());

        return chatClient.prompt()
                .user(u -> u.text("""
                        You are an expert Software Architect.
                        Based on the following project context, recommend the best tech stack:
                        {context}

                        Provide the response strictly following this structure:
                        {format}
                        """)
                        .param("context", promptContext)
                        .param("format", converter.getFormat()))
                .call()
                .entity(StackReport.class);
    }

}
