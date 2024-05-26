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

    @RequestMapping(value = "/viewTrackPoint")
    public String showTrackPoint(Model model)
            throws Exception {

        String sPoint = restTemplate.getForObject(
                "http://localhost:8080/viewTrackPoint", String.class);

        String[] arrPoints = sPoint.split(",\n");
        List<TrackPoint> trackPoints = new ArrayList<TrackPoint>();

        for (String point : arrPoints) {
            trackPoints.add(convertServuce.decodeDTOTrackPoint(point));
        }

        model.addAttribute("trackPoints", trackPoints);
        return "view-TrackPoint";
    }

    @GetMapping(value = "/viewUser")
    public String showUser(Model model)
            throws Exception {

        String sUser = restTemplate.getForObject(
                "http://localhost:8080/viewUsers", String.class);

        String[] arrUsers = sUser.split(",\n");
        List<User> users = new ArrayList<User>();

        for (String user : arrUsers) {
            users.add(convertServuce.decodeDTOUser(user));
        }

        /*String table ="{\"id\":1,\"userName\":\"Иванов\",\"password\":\"1\",\"roles\":\"CLIENT\"}";

        List<User> users = new ArrayList<User>();
        users.add(convertServuce.decodeDTOUser(table));*/

        model.addAttribute("users", users);
        return "view-User";
    }

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

        User user = convertServuce.decodeDTOUser(table);

        model.addAttribute("userPost", user);
        return "edit-User";
    }

    @PostMapping(value = "/updateUser")
    private String updateUser(
            @ModelAttribute User userPost,
            Model model) throws Exception {

        String sUser = restTemplate.postForObject(
                "http://localhost:8080/udateUsers",userPost.toJson(), String.class);

        String[] arrUsers = sUser.split(",\n");
        List<User> users = new ArrayList<User>();

        for (String user : arrUsers) {
            users.add(convertServuce.decodeDTOUser(user));
        }

        model.addAttribute("users", users);
        return "view-User";
    }


    @RequestMapping(value = "/updateUser1")
    public String UpdateUser(/*@PathVariable("user") User userPost,*/ Model model)
            throws Exception {

        /*String table = userPost.toJson();
        String sUser = restTemplate.postForObject(
                "http://localhost:8080/udateUsers",table, String.class);

        String[] arrUsers = sUser.split(",\n");
        List<User> Users = new ArrayList<User>();

        for (String user : arrUsers) {
            Users.add(convertServuce.decodeDTOUser(user));
        }

        model.addAttribute("users", Users);*/
        return "view-User";
    }




}
