package jdev.tracker.service;

import jdev.dto.Point;
import jdev.tracker.dao.TrackPoint;
import jdev.tracker.dao.repo.PointRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by artem on 20.01.24.
 * Сервис хранения сообщений
 */

@Service
public class DataSaveService  {

    /*
     *определяем блокирующую, двунаправленную очередь, на обьект типа Point,
     * емкостью 100 элементов
     */
    private BlockingDeque<Point> queue =  new LinkedBlockingDeque<>(100);
    private int putCount;
    int i;

    private static final Logger log = LoggerFactory.
            getLogger(DataSaveService.class);

    private List<TrackPoint> all;

    @Autowired
    PointRepository pointRepository;

    /*заполнение очереди объектами типа Point*/
    public void put(Point point) throws InterruptedException {
        i = putCount++;
        queue.put(point);
        pointRepository.save((TrackPoint)point);
    }

    /*извлечение из очереди объектов типа Point*/
    public Point take() throws InterruptedException {
        return queue.take();
    }

    private void delete(TrackPoint trackPoint) {
        pointRepository.delete(trackPoint);
    }

    private void update(TrackPoint trackPoint, double Latitude,
                        double Longitude,double setAzimuth,double Speed) {
        trackPoint.setId(1);
        trackPoint.setLatitude(5);
        trackPoint.setLongitude(5);
        trackPoint.setAzimuth(5);
        trackPoint.setSpeed(5);
        pointRepository.save(trackPoint);
    }

    private void read() {
        all = (List<TrackPoint>) pointRepository.findAll();

        if (all.size() == 0) {
            log.info("NO RECORDS");
        } else {
            all.stream().forEach(trackPoint -> log.info(trackPoint.toString()));
        }
    }

    private TrackPoint create(double Latitude, double Longitude,
                              double setAzimuth,double Speed)  {
        TrackPoint trackPoint = new TrackPoint();
        trackPoint.setId(1);
        trackPoint.setLatitude(5);
        trackPoint.setLongitude(5);
        trackPoint.setAzimuth(5);
        trackPoint.setSpeed(5);
        return pointRepository.save(trackPoint);
    }
}