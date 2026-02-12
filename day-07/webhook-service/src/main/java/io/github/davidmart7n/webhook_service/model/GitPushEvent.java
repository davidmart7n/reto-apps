package io.github.davidmart7n.webhook_service.model;

import java.io.Serializable;

public record GitPushEvent(
    String repoName,
    String branch,
    String commitId,
    String author
) implements Serializable{} 