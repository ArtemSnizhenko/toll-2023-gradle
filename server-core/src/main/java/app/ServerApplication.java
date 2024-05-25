package app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication                                          //приложение Spring Boot
@ComponentScan({"controllers", "app", "service"})               //производим сканирование комонентов в указаных директориях
public class ServerApplication {

    public static void main(String[] args){
        SpringApplication.run(ServerApplication.class, args);   //запуск приложения
    }
}