package controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import service.ConvertService;
import service.JpaInteractionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Класс реализующий RestFull Controller
 */

@RestController
//@EnableAutoConfiguration
public class RestFullController {

    private static final Logger log = LoggerFactory.
            getLogger(GPSController.class);

    @Autowired
    JpaInteractionService jpaApplicationService;

    @Autowired
    ConvertService convertService;

    /*метод получения информации
    о пройденном маршруте для заданного устройства*/
    @GetMapping("/TrackPoint")                                 // пример запроса http://localhost:8080/TrackPoint?trackerId=777&size=3
    public String getRequestTrackPointRest(
            @RequestParam("trackerId") int trackerId,
            @RequestParam("size") int size) throws InterruptedException,
            JsonProcessingException {

        return convertService.TrackPointtoJSON(
                jpaApplicationService.take(trackerId,size));
    }
}
