package jdev.tracker.service;

import jdev.tracker.dao.TrackPoint;
import jdev.tracker.dao.repo.PointRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Created by artem on 20.01.24.
 * Сервис хранения сообщений
 */

@Service
public class DataSaveService  {

    private static final Logger log = LoggerFactory.
            getLogger(DataSaveService.class);

    private List<TrackPoint> all;

    @Autowired
    PointRepository pointRepository;

    /*заполнение базы объектами типа TrackPoint*/
    public void put(TrackPoint trackPoint) throws InterruptedException {
        pointRepository.save(trackPoint);
    }

    /*извлечение из базы объектов типа TrackPoint*/
    public TrackPoint take() throws InterruptedException {
        TrackPoint trackPoint;
        all = (List<TrackPoint>) pointRepository.findAll();
        int size = all.size();
        if (size == 0) {
            log.info("NO RECORDS");
            trackPoint = null;
        } else {
            trackPoint = all.get(size-1);
        }
        return trackPoint;
    }

    /*удаление из БД*/
    public void delete(TrackPoint trackPoint) {
        pointRepository.delete(trackPoint);
    }

    private TrackPoint create(TrackPoint point) {
//        trackPoint.setModel(model);
        return pointRepository.save(point);
    }
}