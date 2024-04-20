package jdev.tracker.service;

import jdev.dto.Point;
import jdev.tracker.dao.TrackPoint;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

/**
 * Created by artem on 16.03.24.
 */
@RunWith(MockitoJUnitRunner.class)
public class DataSendServiceTest {
    Point point = new Point();
    {point.setLatitude(56);
        point.setLongitude(34);
        point.setAzimuth(44);
        point.setSpeed(90);}

    @Mock
    RestTemplate restTemplate;

    @Mock
    DataSaveService dataSaveService;

    @InjectMocks
    DataSendService mockedDataSendService;

    @Test
    public void sendData() throws Exception {
        when(dataSaveService.take()).thenReturn((TrackPoint)point);
        when(restTemplate.postForObject("http://localhost:8080/server_core",
                point.toJson(), String.class)).thenReturn("{\"success\":true}");
        String result = mockedDataSendService.sendData();
        assertNotNull(result);
        assertEquals("{\"success\":true}",result);
    }

    /*Интеграционный тест с tracker-core*/
    @Test
    public void sendDataIntegration() throws Exception {
        DataSaveService dataSaveService = new DataSaveService();
        dataSaveService.put(point);
        String result = new DataSendService(new RestTemplate(),dataSaveService).sendData();
        assertNotNull(result);
        assertEquals("{\"success\":true}", result);
    }
}