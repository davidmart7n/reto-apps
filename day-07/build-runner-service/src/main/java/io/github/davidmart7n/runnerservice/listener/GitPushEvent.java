package io.github.davidmart7n.runnerservice.listener;

import java.io.Serializable;

public record GitPushEvent( String repoName, 
                            String branch, 
                            String commitId, 
                            String author) implements Serializable {}