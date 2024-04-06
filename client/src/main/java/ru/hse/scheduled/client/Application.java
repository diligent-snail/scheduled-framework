package ru.hse.scheduled.client;

import ru.hse.scheduled.api.Framework;
import ru.hse.scheduled.api.annotation.Scheduled;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ServiceLoader;

public class Application {
    @Scheduled
    private void sayHello() {
        System.out.println("Hello! Time is " + LocalDateTime.now());
    }

    public static void main(String[] args) {
        try (Framework framework = getFramework()) {
            framework.start(Application.class);
            try {
                Thread.sleep(Duration.ofSeconds(5));
            } catch (InterruptedException e) {
                System.out.println("Interrupted during sleep. Message: '" + e.getMessage() + "'");
                e.printStackTrace(System.out);
            }
        }
    }

    private static Framework getFramework() {
        return ServiceLoader.load(Framework.class)
                .findFirst()
                .orElseThrow();
    }

}
