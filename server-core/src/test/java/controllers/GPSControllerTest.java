package controllers;

import org.junit.Test;
import org.junit.runner.Request;
import org.springframework.web.bind.annotation.RequestBody;

import static jdk.nashorn.internal.runtime.regexp.joni.Syntax.Java;
import static org.junit.Assert.*;

/**
 * Created by artem on 14.03.24.
 */
public class GPSControllerTest {
    @Test
    public void postRequestGPS() throws Exception {

        String spoint = "{\"latitude\":47.310336550125555," +
                "\"longitude\":10.855309494913229," +
                "\"azimuth\":908.0," +
                "\"speed\":87.5467299141704}";

        GPSController controller = new GPSController();
        String result = controller.postRequestGPS(spoint);
        assertEquals("{\"success\":true}", result);
    }

}