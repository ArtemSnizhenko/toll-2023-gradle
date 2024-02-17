package jdev.tracker.service;

import jdev.dto.Point;
import jdev.tracker.KMLTrek;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/*
 * Сервис получения координат GPS
 * Created by artem on 20.01.24.
 */

@Service
public class GPSDataService {

    private KMLTrek kmlreadTrek = new KMLTrek();
    private double random = new Random().nextDouble();

    /*связываем с сервисом сохранения данных*/
    @Autowired
    private DataSaveService dataSaveService;

    /*вызов по расписанию*/
    @Scheduled(cron = "${cron.prop_gps}")
    private void tick() throws Exception {

        /*эмуляция значений широты, долготы, азимута, скорости*/
        double[] tempArr = kmlreadTrek.getCoordinates();    //читаем координаты с .KML файла
        double longitude = tempArr[0];
        double latitude = tempArr[1];
        double azimuth = tempArr[2];
        double speed = 60 + (random * (95 - 60));           //скорсть задаем рандомно
        Point point = new Point();
        point.setLatitude(latitude);
        point.setLongitude(longitude);
        point.setAzimuth(azimuth);
        point.setSpeed(speed);
        dataSaveService.put(point);                         //сохранение сообщений
    }
}

