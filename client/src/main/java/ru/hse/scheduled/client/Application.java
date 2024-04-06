package ru.hse.scheduled.client;

import ru.hse.scheduled.api.Framework;
import ru.hse.scheduled.api.annotation.Scheduled;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ServiceLoader;

/**
 * The demo application
 */
public class Application {
    /**
     * Demo {@link Scheduled} method.
     * Runs each 1s
     */
    @Scheduled
    private void sayHello() {
        System.out.println("Hello! Time is " + LocalDateTime.now());
    }

    /**
     * The application entry point
     *
     * @param args the application arguments
     */
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

    /**
     * Gets the first found {@link Framework} implementation using the {@link ServiceLoader}
     *
     * @return framework implementation
     */
    private static Framework getFramework() {
        return ServiceLoader.load(Framework.class)
                .findFirst()
                .orElseThrow();
    }
}
