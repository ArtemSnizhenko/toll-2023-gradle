package jd.dao;

import jdev.dto.Point;

public class TrackPoint extends Point {

    int id;

    int trackerId;

    double latitude;

    double longitude;

    double azimuth;

    double speed;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    @Override
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
