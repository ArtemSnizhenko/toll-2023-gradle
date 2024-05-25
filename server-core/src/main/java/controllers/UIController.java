package controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dao.TrackPoint;
import dao.User;
import org.springframework.web.bind.annotation.RequestParam;
import service.ConvertServuce;
import service.JpaInteractionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by artem on 22.05.24.
 */
public class UIController {

    private static final Logger log = LoggerFactory.
            getLogger(GPSController.class);

    @Autowired
    JpaInteractionService jpaApplicationService;

    /*@Autowired
    ConvertServuce convertServuce;

    *//*метод на Get запрос возвращает значения таблиц в формате JSON*//*
    @GetMapping("/viewTables")
    public String getRequestTrackPointView(
            *//*@RequestParam("obj") String obj*//*) throws InterruptedException,
            JsonProcessingException {
        String ssss = "sdf";

        return convertServuce.toJSONTrackPoint(
                jpaApplicationService.readTableTrackPoint());
        *//*if (obj == "TrackPoint"){
            return convertServuce.toJSONTrackPoint(
                    jpaApplicationService.readTableTrackPoint());
        }else if (obj == "User"){
            return convertServuce.toJSONUser(
                    jpaApplicationService.readTableUser());
        }else{
            return "null";
        }*//*
    }*/

   /* *//*метод на Get запрос возвращает всю таблицу User в формате JSON*//*
    @GetMapping("/viewUsers")
    public String getRequestUsertView() throws InterruptedException,
            JsonProcessingException {
        return convertServuce.toJSONUser(
                jpaApplicationService.readTableUser());
    }*/
}
