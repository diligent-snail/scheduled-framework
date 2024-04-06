package ru.hse.scheduled.api;

public interface Framework extends AutoCloseable {
    void start(Class<?> clazz);

    @Override
    void close();
}
