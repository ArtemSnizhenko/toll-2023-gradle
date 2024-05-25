package jd.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import jd.dao.TrackPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @RequestMapping(value = "/viewTable")
    public String showTable(Model model/*, @RequestParam("table") String table*/)
            throws Exception {
        String sPoint = restTemplate.getForObject(
                "http://localhost:8080/viewTables", String.class);

        String[] arrPoints = sPoint.split(",\n");

        List<TrackPoint> trackPoints = new ArrayList<TrackPoint>();

        for (String point : arrPoints) {
            trackPoints.add(decodeDTO(point));
        }

        model.addAttribute("trackPoints", trackPoints);
        return "view-TrackPoint";

    }

    public TrackPoint decodeDTO(String json) throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, TrackPoint.class);
    }

}
