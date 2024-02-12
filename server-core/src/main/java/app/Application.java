package app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication //создаем приложение Spring Boot
@ComponentScan({"controllers", "app"}) //производим сканирование комонентов в указаных директориях
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
