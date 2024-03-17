package jdev.tracker.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Сервис отправки сообщений по расписанию
 * Created by artem on 20.01.24.
 */

@Service
public class DataSendService {

    private static final Logger log = LoggerFactory.
            getLogger(DataSendService.class);
    private final DataSaveService dataSaveService;
    private final RestTemplate restTemplate;

    @Autowired
    public DataSendService(RestTemplate restTemplate, DataSaveService dataSaveService) {
        this.restTemplate = restTemplate;
        this.dataSaveService = dataSaveService;
    }

    /*отправка данных по расписанию*/
    @Scheduled(cron = "${cron.prop_send}")
    public String sendData() throws Exception {
        String pointJson = dataSaveService.take().toJson();
        /*
         * выполнения POST запроса, в теле которого передаются координаты
         * в формате JSON
         */
        String response = restTemplate
            .postForObject("http://localhost:8080/server_core",
                            pointJson, String.class);
        log.info(response);                                             //log ответа
        return response;
    }
}
