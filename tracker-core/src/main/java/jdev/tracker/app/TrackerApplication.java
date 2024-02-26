package jdev.tracker.app;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by artem on 27.12.23.
 * основной класс Spring Boot, который запускает tracker-core
 */

/*анотация Spring Boot*/
@SpringBootApplication
/*@ComponentScan({"app","service"})*/
public class TrackerApplication {

    public static void main(String... args) throws Exception {
        ApplicationContext context = new
                AnnotationConfigApplicationContext(InjectionContext.class);
    }
}