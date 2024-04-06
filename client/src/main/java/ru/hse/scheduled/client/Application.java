package ru.hse.scheduled.client;

import ru.hse.scheduled.api.Framework;
import ru.hse.scheduled.api.annotation.Scheduled;

import java.util.ServiceLoader;

public class Application {
    /*@Scheduled
    private void sayHello() {
        // 1. Метод найдем, исключение вылетит, в консоль напечатается
        // 2. Метод найдем, исключение вылетит, в консоль не напечатается
        // 3. Метод не найдем
        System.out.println("Hello!");
    }*/

    public static void main(String[] args) {
        System.out.println(Application.class.getModule());
        // new SimpleFramework();
        /*Framework framework = ServiceLoader.load(Framework.class)
                        .findFirst()
                                .orElseThrow();
        framework.start(Application.class);*/
    }
}
