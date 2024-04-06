package ru.hse.scheduled.impl;

public class PrintExceptionRunnable implements Runnable {
    private final Runnable runnable;

    public PrintExceptionRunnable(Runnable runnable) {
        this.runnable = runnable;
    }

    @Override
    public void run() {
        try {
            runnable.run();
        } catch (Throwable throwable) {
            System.err.println(throwable.getMessage());
            throwable.printStackTrace(System.err);
        }
    }
}
