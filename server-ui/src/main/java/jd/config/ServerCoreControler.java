package jd.config;

import jd.dao.TrackPoint;
import jd.dao.User;
import jd.service.ConvertServuce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by artem on 18.05.24.
 */

/*Контроллера для взаимодействия с таблицами Server-core*/
@Controller
public class ServerCoreControler {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    ConvertServuce convertServuce;

    /*Отображение TrackPoint*/
    @GetMapping(value = "/viewTrackPoint")
    public String showTrackPoint(Model model)
            throws Exception {

        String str = restTemplate.getForObject(
                "http://localhost:8080/viewTrackPoint", String.class);

        String[] arrStr = str.split(",\n");
        List<TrackPoint> objList = new ArrayList<TrackPoint>();

        for (String item : arrStr) {
            objList.add(convertServuce.decodeDTOTrackPoint(item));
        }

        model.addAttribute("trackPoints", objList);
        return "view-TrackPoint";
    }

    /*Отображение User*/
    @GetMapping(value = "/viewUser")
    public String showUser(Model model)
            throws Exception {

        String str = restTemplate.getForObject(
                "http://localhost:8080/viewUsers", String.class);

        String[] arrStr = str.split(",\n");
        List<User> objList = new ArrayList<User>();

        for (String item : arrStr) {
            objList.add(convertServuce.decodeDTOUser(item));
        }

        model.addAttribute("users", objList);
        return "view-User";
    }

    /*Реадактирование User*/
    @GetMapping(value = "/editUser")
    private String editUser(
            @RequestParam("id") String id,
            @RequestParam("userName") String userName,
            @RequestParam("password") String password,
            @RequestParam("roles") String roles,
            Model model) throws Exception {

        String table="{" +
                "\"id\":"+id+"," +
                "\"userName\":\""+userName+"\"," +
                "\"password\":\""+password+"\"," +
                "\"roles\":\""+roles+"\"}";
        User obj = convertServuce.decodeDTOUser(table);
        model.addAttribute("userPost", obj);
        return "edit-User";
    }

    /*Реадактирование TrackPoint*/
    @GetMapping(value = "/editTrackPoint")
    private String editTrackPoint(
            @RequestParam("id") String id,
            @RequestParam("trackerId") String trackerId,
            @RequestParam("latitude") String latitude,
            @RequestParam("longitude") String longitude,
            @RequestParam("azimuth") String azimuth,
            @RequestParam("speed") String speed,
            Model model) throws Exception {

        String table="{" +
                "\"id\":"+id+"," +
                "\"trackerId\":\""+trackerId+"\"," +
                "\"latitude\":\""+latitude+"\"," +
                "\"longitude\":\""+longitude+"\"," +
                "\"azimuth\":\""+azimuth+"\"," +
                "\"speed\":\""+speed+"\"}";
        TrackPoint obj = convertServuce.decodeDTOTrackPoint(table);
        model.addAttribute("trackPointPost", obj);
        return "edit-TrackPoint";
    }

    /*Обновление  User*/
    @PostMapping(value = "/updateUser")
    private String updateUser(
            @ModelAttribute User userPost,
            Model model) throws Exception {

        String str = restTemplate.postForObject(
                "http://localhost:8080/updateUser",userPost.toJson(), String.class);

        String[] arrStr = str.split(",\n");
        List<User> objList = new ArrayList<User>();

        for (String item : arrStr) {
            objList.add(convertServuce.decodeDTOUser(item));
        }

        model.addAttribute("users", objList);
        return "view-User";
    }

    /*Обновление  TrackPoint*/
    @PostMapping(value = "/updateTrackPoint")
    private String updateTrackPoint(
            @ModelAttribute TrackPoint trackPointPost,
            Model model) throws Exception {

        String str = restTemplate.postForObject(
                "http://localhost:8080/updateTrackPoint",trackPointPost.toJson(), String.class);

        String[] arrStr = str.split(",\n");
        List<TrackPoint> objList = new ArrayList<TrackPoint>();

        for (String item : arrStr) {
            objList.add(convertServuce.decodeDTOTrackPoint(item));
        }

        model.addAttribute("trackPoints", objList);
        return "view-TrackPoint";
    }

    /*Удаление User*/
    @GetMapping(value = "/deletUser")
    private String deletUser(
            @RequestParam("id") String id,
            @RequestParam("userName") String userName,
            @RequestParam("password") String password,
            @RequestParam("roles") String roles,
            Model model) throws Exception {

        String table="{" +
                "\"id\":"+id+"," +
                "\"userName\":\""+userName+"\"," +
                "\"password\":\""+password+"\"," +
                "\"roles\":\""+roles+"\"}";

        String str = restTemplate.postForObject(
                "http://localhost:8080/deletUser",table, String.class);

        String[] arrStr = str.split(",\n");
        List<User> objList = new ArrayList<User>();

        for (String item : arrStr) {
            objList.add(convertServuce.decodeDTOUser(item));
        }

        model.addAttribute("users", objList);
        return "view-User";
    }

    /*Удаление TrackPoint*/
    @GetMapping(value = "/deletTracPoint")
    private String deletTrackPoint(
            @RequestParam("id") String id,
            @RequestParam("trackerId") String trackerId,
            @RequestParam("latitude") String latitude,
            @RequestParam("longitude") String longitude,
            @RequestParam("azimuth") String azimuth,
            @RequestParam("speed") String speed,
            Model model) throws Exception {

        String table="{" +
                "\"id\":"+id+"," +
                "\"trackerId\":\""+trackerId+"\"," +
                "\"latitude\":\""+latitude+"\"," +
                "\"longitude\":\""+longitude+"\"," +
                "\"azimuth\":\""+azimuth+"\"," +
                "\"speed\":\""+speed+"\"}";

        String str = restTemplate.postForObject(
                "http://localhost:8080/deletUser",table, String.class);

        String[] arrStr = str.split(",\n");
        List<TrackPoint> objList = new ArrayList<TrackPoint>();

        for (String item : arrStr) {
            objList.add(convertServuce.decodeDTOTrackPoint(item));
        }

        model.addAttribute("trackPoints", objList);
        return "view-TrackPoint";
    }

}
