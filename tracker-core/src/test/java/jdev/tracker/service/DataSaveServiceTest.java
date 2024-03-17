package jdev.tracker.service;

import jdev.dto.Point;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by artem on 14.03.24.
 */
public class DataSaveServiceTest {

    @Test
    public void DataSave() throws Exception {
        Point point = new Point();
        point.setLatitude(56);
        point.setLongitude(34);
        point.setAzimuth(44);
        point.setSpeed(90);
        DataSaveService dataSaveService= new DataSaveService();
        dataSaveService.put(point);
        String pointJson = dataSaveService.take().toJson();
        assertTrue(pointJson.contains("\"latitude\":56.0"));
    }
}