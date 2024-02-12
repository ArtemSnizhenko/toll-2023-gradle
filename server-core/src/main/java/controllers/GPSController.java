package controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController // создаем контроллер
public class GPSController {

    private static final Logger log = LoggerFactory.getLogger(GPSController.class);

    @PostMapping(value = "/server-core") //определяем метод для приема координат в виде POST запросов
    public String postRequestGPS(@RequestBody String spoint) throws JsonProcessingException {
        log.info(spoint);
        return new Response(true).toJson(); //возвращаем ответ на POST запрос в формате JSON
    }
}