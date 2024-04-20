package jdev.tracker.service;

import jdev.tracker.dao.TrackPoint;
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
//    @Scheduled(cron = "${cron.prop_send}")
    public String sendData() throws Exception {

        TrackPoint trackPoint = dataSaveService.take();
           /*
         * выполнения POST запроса, в теле которого передаются координаты
         * в формате JSON
         */
//        log.info("RECORDSIS==" + trackPoint.toJson());
        String response = restTemplate
            .postForObject("http://localhost:8080/server_core",
                    trackPoint.toJson(), String.class);

        log.info(response);

        /*удаление передаваемой сущности из базы при подтверждение успешной передачи*/
        if (response.contains("{\"success\":true}")) {
            dataSaveService.delete(trackPoint);
            log.info("DELET_ROW");//log удаления уже отправленной строки
        }
//        System.out.print(response);//log ответа
        return response;
    }
}
