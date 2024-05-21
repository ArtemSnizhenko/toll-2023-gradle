package jdev.tracker.app;

import jdev.tracker.service.DataPeekService;
import jdev.tracker.service.DataSaveService;
import jdev.tracker.service.DataSendService;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.web.client.RestTemplate;

/**
 * класс конфигурации
 */

@EnableJpaRepositories("jdev.tracker.dao")
@EntityScan(basePackageClasses = jdev.tracker.dao.TrackPoint.class)
@ComponentScan(basePackages = {"jdev.tracker.app","jdev.tracker.dao","jdev.tracker.service"})
@EnableScheduling
@Configuration
@PropertySource({"classpath:/application.properties", "classpath:/application-${spring.profiles.active}.properties"})
public class InjectionContext {

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
       /*создаем компонент restTemplate - клиент REST*/
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    /*сервис генерации данных*/
    @Bean
    public DataPeekService gps(){
        return new DataPeekService();
    }

    /*сервис сохранения данных*/
    @Bean
    public DataSaveService saveService(){
        return new DataSaveService();
    }

    /*сервис отправки данных*/
    @Bean
    public DataSendService sendService(){
        return new DataSendService(restTemplate(),saveService());
    }

    /*настройка размера очереди*/
    @Bean
    public TaskScheduler poolScheduler() {
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setThreadNamePrefix("poolScheduler");
        scheduler.setPoolSize(2);
        return scheduler;
    }
}
