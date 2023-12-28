package jdev.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by artem on 26.12.23.
 */
public class PointDTOTest {

    private String excepted = "{\"lat\":56.0,\"lon\":74.0,\"autoId\":\"o567gfd\",\"time\":1703691256995}";
    private String autoId = "o567gfd";

    //обьект в строку
    @Test
    public void toJson() throws Exception {
        PointDTO point = new PointDTO();
        point.setLat(56);
        point.setLon(74);
        point.setAutoId("o567gfd");
        point.setTime(System.currentTimeMillis());
        assertTrue(point.toJson().contains("\"lat\":56"));
        assertTrue(point.toJson().contains("\"time\":"));
        System.out.println(point.toJson());
    }

    //строку в обьект
    @Test
    public void decodeDTO() throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        PointDTO dto = mapper.readValue(excepted,PointDTO.class);
        //проверка
        assertEquals(autoId,dto.getAutoId());
        assertEquals(1703691256995L,dto.getTime());
        System.out.println(dto.getLat());
    }
}
