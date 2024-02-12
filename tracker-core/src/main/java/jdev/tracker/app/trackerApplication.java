package jdev.tracker.app;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by artem on 27.12.23.
 */

@SpringBootApplication //это приложение Spring Boot
public class trackerApplication {

    public static void main(String... args) throws Exception {
        ApplicationContext context = new AnnotationConfigApplicationContext(InjectionContext.class);
    }
}
