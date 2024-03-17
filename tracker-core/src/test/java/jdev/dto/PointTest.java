package jdev.dto;


import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by artem on 27.12.23.
 */
public class PointTest {

    @Test
    public void toJson() throws Exception {
        Point point = new Point();
        point.setLatitude(56);
        point.setLongitude(74);
        assertTrue(point.toJson().contains("\"latitude\":56"));
        System.out.println(point.toJson());
    }
}
