package jdev.tracker;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by artem on 27.12.23.
 */
public class Main {


    public static void main(String... args) throws Exception {

        ApplicationContext context = new AnnotationConfigApplicationContext(InjectionContext.class);
    }

}
