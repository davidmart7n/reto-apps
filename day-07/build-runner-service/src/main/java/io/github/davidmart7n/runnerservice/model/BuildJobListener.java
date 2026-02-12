package io.github.davidmart7n.runnerservice.model;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import io.github.davidmart7n.runnerservice.listener.GitPushEvent;

@Component
public class BuildJobListener {


    @RabbitListener(bindings = @QueueBinding(
        value = @Queue(value = "build-runner-queue", durable = "true"),
        exchange = @Exchange(value = "git-events-exchange", type = "topic"),
        key = "repo.push"
    ))
    public void startBuildJob(GitPushEvent event) throws InterruptedException {
        System.out.println("🔨 [NUEVO TRABAJO] Recibido evento de: " + event.repoName());
        
        // Simulamos un proceso largo (Build)
        System.out.println("⚙️  Compilando rama '" + event.branch() + "' (Autor: " + event.author() + ")...");
        Thread.sleep(2000); // Espera 2 segundos (simulación)
        
        System.out.println("✅  Build completado exitosamente para commit: " + event.commitId());
        System.out.println("--------------------------------------------------");
    }
}
