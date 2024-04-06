package ru.hse.scheduled.api.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * {@link ru.hse.scheduled.api.Framework} runs the methods with the {@link Scheduled} annotation at {@link #fixedRate()}.
 * <p>
 * The following is required to run the {@link Scheduled} method:
 * <ol>
 *     <li>The method annotated with the {@link Scheduled} annotation must accept no arguments</li>
 *     <li>The class containing the {@link Scheduled} method must have a constructor without parameters</li>
 *     <li>The parameterless constructor and the {@link Scheduled} method must be accessible through reflection
 *     to the framework implementation</li>
 * </ol>
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Scheduled {
    /**
     * The fixed rate at which the {@link Scheduled} method must be run.
     * Must be {@code >= 0}
     *
     * @return the fixed rate
     */
    long fixedRate() default 1_000;
}
