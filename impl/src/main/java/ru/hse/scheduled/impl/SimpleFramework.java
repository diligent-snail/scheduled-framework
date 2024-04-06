package ru.hse.scheduled.impl;

import ru.hse.scheduled.api.Framework;
import ru.hse.scheduled.api.annotation.Scheduled;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SimpleFramework implements Framework {
    private final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

    @Override
    public void start(Class<?> clazz) {
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            Scheduled annotation = method.getAnnotation(Scheduled.class);
            if (annotation != null) {
                Object object = newInstance(clazz);
                long fixedRate = annotation.fixedRate();
                executorService.scheduleAtFixedRate(
                        new PrintExceptionRunnable(() -> invokeMethod(method, object)), 0, fixedRate, TimeUnit.MILLISECONDS);

            }
        }
    }

    private static void invokeMethod(Method method, Object object) {
        try {
            method.setAccessible(true);
            method.invoke(object);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    private static Object newInstance(Class<?> clazz) {
        try {
            Constructor<?> constructor = clazz.getDeclaredConstructor();
            return constructor.newInstance();
        } catch (ReflectiveOperationException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void close() {
        executorService.close();
    }
}
