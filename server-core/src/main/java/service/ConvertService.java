package service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


import dao.TrackPoint;
import dao.User;
import org.springframework.stereotype.Service;

/**
 * Класс предназначен для преобразования из одного
 * типа данных в другой
 */

@Service
public class ConvertService {

    /* Сериализация в JSON и десериализация*/
    public String TrackPointtoJSON(Iterable<TrackPoint> objects) throws
            JsonProcessingException {

        String strJsonResult = "";
        for (TrackPoint object : objects) {
            strJsonResult = strJsonResult + object.toJson() + ",\n";
        }
        return strJsonResult;
    }

    public String UsertoJSON(Iterable<User> objects) throws
            JsonProcessingException {

        String strJsonResult = "";
        for (User object : objects) {
            strJsonResult = strJsonResult + object.toJson() + ",\n";
        }
        return strJsonResult;
    }

    /* преобразовываем строку JSON в обьект TrackPoint*/
    public TrackPoint decodeDTOTrackPoint(String json) throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, TrackPoint.class);
    }

    /*преобразовываем строку JSON в обьект User*/
    public User decodeDTOUser(String json) throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, User.class);
    }
}
