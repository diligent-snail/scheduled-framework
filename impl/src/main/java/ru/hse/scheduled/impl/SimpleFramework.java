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
    private final ScheduledExecutorService executorService
            = Executors.newSingleThreadScheduledExecutor();


    @Override
    public void start(Class<?> clazz) {
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            Scheduled annotation = method.getAnnotation(Scheduled.class);
            if (annotation != null) {
                Constructor<?> constructor = null;
                try {
                    constructor = clazz.getDeclaredConstructor();
                } catch (NoSuchMethodException e) {
                    throw new RuntimeException(e);
                }
                Object object = newInstance(constructor);
                long fixedRate = annotation.fixedRate();


                executorService.scheduleAtFixedRate(
                        new PrintExceptionRunnable(
                                () -> {
                                    try {
                                        method.setAccessible(true);
                                        method.invoke(object);
                                    } catch (IllegalAccessException | InvocationTargetException e) {
                                        throw new RuntimeException(e);
                                    }
                                }), 0, fixedRate, TimeUnit.MILLISECONDS);

            }
        }
    }

    private static Object newInstance(Constructor<?> constructor) {
        Object object;
        try {
            object = constructor.newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
        return object;
    }

    @Override
    public void close() {

    }
}
