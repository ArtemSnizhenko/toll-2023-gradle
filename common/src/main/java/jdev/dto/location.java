package jdev.dto;

import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * Created by artem on 09.01.24.
 * Делегирование
 */
public class location {
    Point point;

    public static void meth(){

    }

    public double getLat() {
        return point.getLatitude();
    }

    public void setLat(double lat) {
        point.setLatitude(lat);
    }

    public double getLon() {
        return point.getLongitude();
    }

    public void setLon(double lon) {
        point.setLongitude(lon);
    }


/*    public String toJson_old() {
        return point.toJson_old();
    }*/

    public String toJson() throws JsonProcessingException {
        return point.toJson();
    }
}
