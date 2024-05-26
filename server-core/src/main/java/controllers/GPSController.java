package controllers;

import dao.Response;

import service.ConvertService;
import service.JpaInteractionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

/**
 * Класс контроллера
 */

@RestController                                                 //контроллер
@EnableAutoConfiguration
public class GPSController {

    private static final Logger log = LoggerFactory.
            getLogger(GPSController.class);

    @Autowired
    JpaInteractionService jpaApplicationService;

    @Autowired
    ConvertService convertService;

    /*определяем метод для приема координат в виде POST запросов*/
    @PostMapping(value = "/server_core")
    public String postRequestGPS(@RequestBody String sTrackPoint)
            throws Exception {
        log.info(sTrackPoint);
        jpaApplicationService.put(convertService.decodeDTOTrackPoint(sTrackPoint));        //сохраняем полученные координаты в БД
        return new Response(true).toJson();                         //возвращаем ответ на POST запрос в формате JSON
    }
}