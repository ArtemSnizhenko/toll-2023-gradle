package jdev.tracker.service;

import jdev.dto.Point;
import jdev.tracker.KMLreadTrek;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;


/**
 * Created by artem on 20.01.24.
 * Сервис GPS
 */

@Service
public class GPSDataService {

    private KMLreadTrek kmlreadTrek = new KMLreadTrek();

    @Autowired //связываем с сервисом сохранения данных
    private DataSaveService dataSaveService;


    @Scheduled(cron = "${cron.prop_gps}")//вызов по расписанию
    private void tick() throws Exception {

        //эмуляция значений широты, долготы, азимута, скорости
        double random = new Random().nextDouble();
        double[] tempArr = kmlreadTrek.getCoordinates();//читаем координаты с .KML файла
        double longitude = tempArr[0];
        double Latitude = tempArr[1];
        double Azimuth = tempArr[2];
        double Speed = 60 + (random * (95 - 60));//скорсть задаем рандомно

        Point point = new Point();
        point.setLatitude(Latitude);
        point.setLongitude(longitude);
        point.setAzimuth(Azimuth);
        point.setSpeed(Speed);

        dataSaveService.put(point); //сохранение сообщений*/
    }
}


