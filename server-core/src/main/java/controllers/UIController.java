package controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import dao.TrackPoint;
import dao.User;
import org.springframework.web.bind.annotation.*;
import service.ConvertService;
import service.JpaInteractionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Класс реализующий RestFull Controller
 * для работы с server-ui
 */

@RestController
public class UIController {

    private static final Logger log = LoggerFactory.
            getLogger(GPSController.class);

    @Autowired
    JpaInteractionService jpaApplicationService;

    @Autowired
    ConvertService convertService;

    /*Получени данных из таблицы User*/
    @GetMapping("/viewUsers")
    public String getRequestUserView() throws InterruptedException,
            JsonProcessingException {

        return convertService.UsertoJSON(
                jpaApplicationService.readTableUser());
    }

    /*метод на Get запрос возвращает значения таблиц в формате JSON*/
    @GetMapping("/viewTrackPoint")
    public String getRequestTrackPointView() throws InterruptedException,
            JsonProcessingException {

        return convertService.TrackPointtoJSON(
                jpaApplicationService.readTableTrackPoint());
    }

    /*Обновление таблицы User*/
    @PostMapping(value = "/updateUser")
    public String getRequestUserUpdate(
            @RequestBody String table) throws Exception {
//        table="{\"id\":1,\"userName\":\"Иванов\",\"password\":\"1\",\"roles\":\"CLIENT\"}";
        User user = convertService.decodeDTOUser(table);
        jpaApplicationService.update(user,
                user.getUserName(),
                user.getPassword(),
                user.getRoles());

        return convertService.UsertoJSON(
                jpaApplicationService.readTableUser());
    }

    /*Обновление таблицы TrackPoint*/
    @PostMapping(value = "/updateTrackPoint")
    public String getRequestTrackPointUpdate(
            @RequestBody String table) throws Exception {

        TrackPoint trackPoint = convertService.decodeDTOTrackPoint(table);
        jpaApplicationService.update(trackPoint,
                trackPoint.getTrackerId(),
                trackPoint.getLatitude(),
                trackPoint.getLongitude(),
                trackPoint.getAzimuth(),
                trackPoint.getSpeed());

        return convertService.TrackPointtoJSON(
                jpaApplicationService.readTableTrackPoint());
    }

    /*удаление объекта таблицы User*/
    @PostMapping(value = "/deletUser")
    public String getRequestUserDelet(
            @RequestBody String table) throws Exception {

        User user = convertService.decodeDTOUser(table);
        jpaApplicationService.delete(user);

        return convertService.UsertoJSON(
                jpaApplicationService.readTableUser());
    }

    /*удаление объекта таблицы TrackPoint*/
    @PostMapping(value = "/deletTrackPoint")
    public String getRequestTrackPointDelet(
            @RequestBody String table) throws Exception {

        TrackPoint trackPoint = convertService.decodeDTOTrackPoint(table);
        jpaApplicationService.delete(trackPoint);

        return convertService.TrackPointtoJSON(
                jpaApplicationService.readTableTrackPoint());
    }
}
