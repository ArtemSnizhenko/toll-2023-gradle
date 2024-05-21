package jdev.tracker.service;

import jdev.tracker.KMLTrek;
import java.util.Random;
import jdev.tracker.dao.TrackPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/*
 * Сервис получения координат GPS
 * Created by artem on 20.01.24.
 */

@Service
public class DataPeekService {

    private static final Logger log = LoggerFactory.
            getLogger(DataSendService.class);

    private KMLTrek kmlreadTrek = new KMLTrek();
    private double random = new Random().nextDouble();

    /*связываем с сервисом сохранения данных*/
    @Autowired
    private DataSaveService dataSaveService;

    @Value("${int.identifer}")
    int identifer;

    /*вызов по расписанию*/
    /*проблем - создается 2 потока в результате чего получатся 2 записи в БД*/
    @Scheduled(fixedRateString = "${task.fixed.rate.millis}")
//    @Scheduled(fixedDelay = 1000)
    private void tick() throws Exception {
        double[] tempArr = kmlreadTrek.getCoordinates();        //читаем координаты с .KML файла

        TrackPoint trackPoint = new TrackPoint();
        trackPoint.setLatitude(tempArr[1]);
        trackPoint.setLongitude(tempArr[0]);
        trackPoint.setAzimuth(tempArr[2]);
        trackPoint.setSpeed(60 + (random * (95 - 60)));         //скорсть задаем рандомно
        /*идентификатор tracer-core*/
        trackPoint.setTrackerId(identifer);

        dataSaveService.put(trackPoint);                         //сохранение сообщений
        Thread.sleep(2000);
    }
}

