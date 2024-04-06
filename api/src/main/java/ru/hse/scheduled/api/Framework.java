package ru.hse.scheduled.api;

/**
 * A simple demo framework which {@link #start(Class) starts} the given application, and runs its
 * {@link ru.hse.scheduled.api.annotation.Scheduled} methods
 */
public interface Framework extends AutoCloseable {
    /**
     * Starts the application described by the given {@code clazz}.
     * The given class and its {@link ru.hse.scheduled.api.annotation.Scheduled} methods must be accessibly by the
     * framework implementation
     *
     * @param clazz of the application
     */
    void start(Class<?> clazz);

    /**
     * Shuts down the framework
     */
    @Override
    void close();
}
