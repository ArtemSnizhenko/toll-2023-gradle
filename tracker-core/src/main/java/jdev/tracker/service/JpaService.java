package jdev.tracker.service;
import jdev.dto.Point;

import jdev.tracker.dao.TrackPoint;
import jdev.tracker.dao.repo.PointRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by artem on 31.03.24.
 */

@Service
public class JpaService {

    private static final Logger log = LoggerFactory.
            getLogger(JpaService.class);

    private List<TrackPoint> all;

    @Autowired
    PointRepository pointRepository;

    public void  saveRep (Point point){
        pointRepository.save((TrackPoint)point);
        log.info("=======after save================");
    }

    public void readRep() {
        all = (List<TrackPoint>) pointRepository.findAll();

        if (all.size() == 0) {
            log.info("NO RECORDS");
        } else {
            all.stream().forEach(trackPoint -> log.info(trackPoint.toString()));
        }
        log.info("=======after create================");
    }
}
