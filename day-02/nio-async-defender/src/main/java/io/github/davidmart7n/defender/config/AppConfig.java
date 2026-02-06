package io.github.davidmart7n.defender.config;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AppConfig {
    
    private static final int THREADS = Runtime.getRuntime().availableProcessors();

    private static final ExecutorService EXECUTOR = Executors.newFixedThreadPool(THREADS, r->{
        Thread t = new Thread(r);
        t.setName("Defender-Worker");
        t.setDaemon(true);
        return t;
    });

    public static ExecutorService getExecutor() {
        return EXECUTOR;
    }
}
