package jdev.tracker.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;


/**
 * Created by artem on 20.01.24.
 * Сервис отправки сообщений по расписанию
 */

@Service
public class DataSendService {

    private static final Logger log = LoggerFactory.getLogger(DataSendService.class);

    @Autowired//связываем с сервисом сохранения данных
    private DataSaveService dataSaveService;

    //отправка данных
    @Scheduled(cron = "${cron.prop_send}")//настраиваем отправку по расписанию
    private void sendData() throws Exception {
        String pointJson = dataSaveService.take().toJson(); //формируем сротку в формате JSON
        log.info("take trying!!!");
        log.info(pointJson);
    }
}
