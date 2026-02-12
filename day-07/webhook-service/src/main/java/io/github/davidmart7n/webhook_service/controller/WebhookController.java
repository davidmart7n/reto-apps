package io.github.davidmart7n.webhook_service.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.davidmart7n.webhook_service.config.RabbitConfig;
import io.github.davidmart7n.webhook_service.model.GitPushEvent;

@RestController
@RequestMapping("api/push")
public class WebhookController {

    private final RabbitTemplate rabbitTemplate;

    public WebhookController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @PostMapping()
    public String createGitMessage(@RequestBody GitPushEvent event) {

        System.out.println("📥 Recibido push del repo: " + event.repoName());

        rabbitTemplate.convertAndSend(
                RabbitConfig.EXCHANGE_NAME,
                "repo.push",
                event);
        return "✅ Evento de Push recibido y encolado correctamente.";

    }
}
