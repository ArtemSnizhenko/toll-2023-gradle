package jdev.tracker.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URL;

/**
 * Created by artem on 20.01.24.
 * Сервис отправки сообщений по расписанию
 */

@Service
public class DataSendService {

    private static final Logger log = LoggerFactory.getLogger(DataSendService.class);

    @Autowired//связываем с сервисом сохранения данных
    private DataSaveService dataSaveService;

    @Autowired
    RestTemplate restTemplate;

    //отправка данных
    @Scheduled(cron = "${cron.prop_send}")//настраиваем отправку по расписанию
    private void sendData() throws Exception {
        String pointJson = dataSaveService.take().toJson(); //формируем сротку в формате JSON

        //выполнения POST запроса, в теле которого передаются координаты в формате JSON
        String response = restTemplate.postForObject("http://localhost:8080/server-core", pointJson, String.class);
        log.info(response); //log ответа
    }
}
