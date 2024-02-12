package jdev.tracker.app;

import jdev.tracker.service.GPSDataService;
import jdev.tracker.service.DataSaveService;
import jdev.tracker.service.DataSendService;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.web.client.RestTemplate;


/**
 * Created by artem on 20.01.24.
 */

@Configuration
@EnableScheduling
@PropertySource("classpath:/app.properties")
public class InjectionContext {

    @Bean//сервис генерации данных
    public GPSDataService gps(){
        return new GPSDataService();
    }

    @Bean//сервис сохранения данных
    public DataSaveService saveService(){
        return new DataSaveService();
    }

    @Bean//сервис отправки данных
    public DataSendService sendService(){
        return new DataSendService();
    }

    @Bean//настройка размера очереди
    public TaskScheduler poolScheduler() {
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setThreadNamePrefix("poolScheduler");
        scheduler.setPoolSize(20);
        return scheduler;
    }

    @Bean //создаем сомпонент restTemplate - клиент REST
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
