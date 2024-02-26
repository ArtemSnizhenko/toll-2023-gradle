package jdev.tracker.app;

import jdev.tracker.service.GPSDataService;
import jdev.tracker.service.DataSaveService;
import jdev.tracker.service.DataSendService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.web.client.RestTemplate;

/**
 * Created by artem on 20.01.24.
 * класс конфигурации
 */

@Configuration
@EnableScheduling
@PropertySource("classpath:/app.properties")
public class InjectionContext {

    /*сервис генерации данных*/
    @Bean
    public GPSDataService gps(){
        return new GPSDataService();
    }

    /*сервис сохранения данных*/
    @Bean
    public DataSaveService saveService(){
        return new DataSaveService();
    }

    /*сервис отправки данных*/
    @Bean
    public DataSendService sendService(){
        return new DataSendService();
    }

    /*настройка размера очереди*/
    @Bean
    public TaskScheduler poolScheduler() {
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setThreadNamePrefix("poolScheduler");
        scheduler.setPoolSize(20);
        return scheduler;
    }

    /*создаем компонент restTemplate - клиент REST*/
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
