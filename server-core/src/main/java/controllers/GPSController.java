package controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dao.TrackPoint;
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
    public String postRequestGPS(@RequestBody String strackPoint)
            throws Exception {
        log.info(strackPoint);
        jpaApplicationService.put(decodeDTO(strackPoint));      //сохраняем полученные координаты в БД
        return new Response(true).toJson();             //возвращаем ответ на POST запрос в формате JSON
    }

    //преобразовываем строку JSON в обьект
    public TrackPoint decodeDTO(String json) throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, TrackPoint.class);
    }

    /*метод получения информации
    о пройденном маршруте для заданного устройства*/
     @GetMapping("/point")
     public String getrequestpoint(
             @RequestParam("trackerId") int trackerId,
             @RequestParam("size") int size) throws InterruptedException,
             JsonProcessingException{
         Iterable<TrackPoint> tracksPoint = jpaApplicationService.take(trackerId,size);

         String strJsonResulr = "";
         for (TrackPoint trackPoint : tracksPoint) {
             strJsonResulr = strJsonResulr + trackPoint.toJson() + "\n";
             log.info(trackPoint.toString());
         }
         return strJsonResulr;
     }
}