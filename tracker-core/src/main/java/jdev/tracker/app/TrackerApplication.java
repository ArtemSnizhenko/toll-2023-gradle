package jdev.tracker.app;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by artem on 27.12.23.
 * основной класс Spring Boot, который запускает tracker-core
 */

/*анотация Spring Boot*/
@SpringBootApplication
public class TrackerApplication {

    public static void main(String... args) throws Exception {
        ApplicationContext context = new
                AnnotationConfigApplicationContext(InjectionContext.class);
//        context.getEnvironment().setActiveProfiles("production");
//        System.out.print("Profile="+context.getEnvironment().getActiveProfiles());

    }
}