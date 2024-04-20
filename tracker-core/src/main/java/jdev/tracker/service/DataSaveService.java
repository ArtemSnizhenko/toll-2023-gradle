package jdev.tracker.service;


import jdev.dto.Point;
import jdev.tracker.dao.TrackPoint;
import jdev.tracker.dao.repo.PointRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;


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
/*    private BlockingDeque<TrackPoint> queue =  new LinkedBlockingDeque<>(100);
    private int putCount;
    int i;*/

    /*идентификатор tracer-core*/
    @Value("${int.identifer}")
    int identifer;

    private static final Logger log = LoggerFactory.
            getLogger(DataSaveService.class);

    private List<TrackPoint> all;

    @Autowired
    PointRepository pointRepository;

    /*заполнение базы объектами типа TrackPoint*/
    public void put(TrackPoint trackPoint) throws InterruptedException {
//        i = putCount++;
        log.info("=====DataSaveService=======");
//        queue.put(trackPoint);
        pointRepository.save(trackPoint);
    }

    /*извлечение из базы объектов типа TrackPoint*/
    public TrackPoint take() throws InterruptedException {
//        pointRepository.deleteAll();
//        return queue.take();
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