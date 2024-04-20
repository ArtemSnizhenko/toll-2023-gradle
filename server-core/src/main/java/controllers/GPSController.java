package controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dao.TrackPoint;
import jdev.dto.Point;
import jpa.JpaApplicationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Класс контроллера
 */

@RestController                                                 //контроллер
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

    /*метод для для получения информации
    о пройденном маршруте для заданного устройства*/

    @GetMapping(value="/POINT")
    public String postRequestPoint(@RequestBody int identifier, int cnt)
            throws JsonProcessingException, InterruptedException {
        log.info("CountPoint = " + cnt+" - "+identifier);
        return jpaApplicationService.take().toJson();
    }
}