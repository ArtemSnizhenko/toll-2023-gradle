package controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import dao.TrackPoint;
import service.ConvertServuce;
import service.JpaInteractionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by artem on 22.05.24.
 */

@RestController
@EnableAutoConfiguration
public class RestFullController {

    private static final Logger log = LoggerFactory.
            getLogger(GPSController.class);

    @Autowired
    JpaInteractionService jpaApplicationService;

    @Autowired
    ConvertServuce convertServuce;

    /*метод получения информации
    о пройденном маршруте для заданного устройства*/
    @GetMapping("/TrackPoint")                                 // пример запроса http://localhost:8080/TrackPoint?trackerId=777&size=3
    public String getRequestTrackPointRest(
            @RequestParam("trackerId") int trackerId,
            @RequestParam("size") int size) throws InterruptedException,
            JsonProcessingException {
        Iterable<TrackPoint> tracksPoint = jpaApplicationService.take(trackerId,size);

        String strJsonResult = "";
        for (TrackPoint trackPoint : tracksPoint) {
            strJsonResult = strJsonResult + trackPoint.toJson() + "\n";
//             log.info(trackPoint.toString());
        }
        return strJsonResult;
            /*return convertServuce.toJSONTrackPoint(
                    jpaApplicationService.take(trackerId,size));*/
    }
}
