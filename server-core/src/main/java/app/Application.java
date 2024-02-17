package app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication                                  //приложение Spring Boot
@ComponentScan({"controllers", "app"})                  //производим сканирование комонентов в указаных директориях
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args); //запуск приложения
    }
}
