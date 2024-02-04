package jdev.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by artem on 26.12.23.
 */
public class PointTest {

    private final String autoId = "o567gfd";
    private final String json = "{\"lat\":56.0,\"lon\":74.0}";


    //обьект в строку
    @Test
    public void encodeDTO() throws Exception {
        Point point = new Point();
        point.setLatitude(56);
        point.setLongitude(74);
        String pointJson = point.toJson();
        assertTrue(pointJson.contains("\"lat\":56"));
        assertTrue(pointJson.contains("\"time\":"));
        System.out.println(pointJson);
    }

    //строку в обьект
    @Test
    public void decodeDTO() throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        Point dto = mapper.readValue(json,Point.class);
        //проверка
        System.out.println(dto.getLatitude());
    }
}
