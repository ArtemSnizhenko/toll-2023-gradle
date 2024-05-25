package jdev.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by artem on 26.12.23.
 */
public class Point {

    int id;
    int trackerId;
    private double latitude; //широта
    private double longitude; //долгота
    private double azimuth; //
    private double speed; //

    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    public int getTrackerId() {
        return trackerId;
    }

    public void setTrackerId(int trackerId) {
        this.trackerId = trackerId;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getAzimuth() {
        return azimuth;
    }

    public void setAzimuth(double azimuth) {
        this.azimuth = azimuth;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public String toJson() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(this);
    }

    public String toString() {
        return "\n" + "TrackPoint{"  + "\n" +
                "id=" + id  + ",\n" +
                "trackerId=" + trackerId + ",\n" +
                "latitude=" + latitude + ",\n" +
                "longitude=" + longitude + ",\n" +
                "azimuth=" + azimuth + ",\n" +
                "speed=" + speed + "\n" +
                '}' + "\n";
    }

}
