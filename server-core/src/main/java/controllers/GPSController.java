package controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dao.TrackPoint;

import jdev.dto.User;
import jpa.JpaApplicationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Класс контроллера
 */

@RestController                                                 //контроллер
@EnableAutoConfiguration
public class GPSController {

    private static final Logger log = LoggerFactory.
            getLogger(GPSController.class);

    @Autowired
    JpaApplicationService jpaApplicationService;

    /*определяем метод для приема координат в виде POST запросов*/
    @PostMapping(value = "/server_core")
    public String postRequestGPS(@RequestBody String sTrackPoint)
            throws Exception {
        log.info(sTrackPoint);
        jpaApplicationService.put(decodeDTO(sTrackPoint));      //сохраняем полученные координаты в БД
        return new Response(true).toJson();             //возвращаем ответ на POST запрос в формате JSON
    }



    /*метод получения информации
    о пройденном маршруте для заданного устройства*/
     @GetMapping("/TrackPoint")                                 // пример запроса http://localhost:8080/TrackPoint?trackerId=777&size=3
     public String getRequestTrackPointRest(
             @RequestParam("trackerId") int trackerId,
             @RequestParam("size") int size) throws InterruptedException,
             JsonProcessingException{
         Iterable<TrackPoint> tracksPoint = jpaApplicationService.take(trackerId,size);

         String strJsonResult = "";
         for (TrackPoint trackPoint : tracksPoint) {
             strJsonResult = strJsonResult + trackPoint.toJson() + "\n";
//             log.info(trackPoint.toString());
         }
         return strJsonResult;
     }

    @GetMapping("/getPoints")
    public String getRequestTrackPointView() throws InterruptedException,
            JsonProcessingException {
         return toJSON(jpaApplicationService.readTable());
    }

    /* Сериализация в JSON и десериализация*/
    private String toJSON(Iterable<TrackPoint> objects) throws
            JsonProcessingException {
        String strJsonResult = "";
        for (TrackPoint object : objects) {
            strJsonResult = strJsonResult + object.toJson() + ",\n";
        }
        return strJsonResult;
    }

    /*Перегрузка метода toJSON*/
    private String toJSON(List<User> objects) throws
            JsonProcessingException {
        String strJsonResult = "";
        for (User object : objects) {
            strJsonResult = strJsonResult + object.toJson() + ",\n";
        }
        return strJsonResult;
    }

    //преобразовываем строку JSON в обьект
    public TrackPoint decodeDTO(String json) throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, TrackPoint.class);
    }



}