package controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
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

    /*метод на Get запрос возвращает значения таблиц в формате JSON*/
    @GetMapping("/viewTrackPoint")
    public String getRequestTrackPointView() throws InterruptedException,
            JsonProcessingException {

        return convertService.TrackPointtoJSON(
                jpaApplicationService.readTableTrackPoint());
    }

    /*метод на Get запрос возвращает всю таблицу User в формате JSON*/
    @GetMapping("/viewUsers")
    public String getRequestUserView() throws InterruptedException,
            JsonProcessingException {
        return convertService.UsertoJSON(
                jpaApplicationService.readTableUser());
    }

    /*метод на Get запрос возвращает всю таблицу User в формате JSON*/
    @GetMapping("/viewUsersId")
    public String getRequestUserId(
            @RequestParam("id") int id) throws InterruptedException,
            JsonProcessingException {
        return jpaApplicationService.getRowUserId(id).toJson();
    }

    /*метод на Get запрос возвращает всю таблицу User в формате JSON*/
    @PostMapping(value = "/udateUsers")
    public String getRequestUserUpdate(
            @RequestBody String table) throws Exception {
//        table="{\"id\":1,\"userName\":\"Иванов\",\"password\":\"1\",\"roles\":\"CLIENT\"}";
        User user = convertService.decodeDTOUser(table);
        jpaApplicationService.update(user,user.getUserName(),user.getPassword(),user.getRoles());
        return convertService.UsertoJSON(
                jpaApplicationService.readTableUser());
    }
}
