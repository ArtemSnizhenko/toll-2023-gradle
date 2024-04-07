package jdev.tracker.dao;

import jdev.dto.Point;

import javax.persistence.*;
import static javax.persistence.GenerationType.AUTO;

@Entity
@Table(name="Tracks")
public class TrackPoint extends Point {

    @Id
    @GeneratedValue(strategy = AUTO)
    @Column(name = "ID")
    int id;

    @Column(name = "LATITUDE")
    double latitude;
    @Column(name = "LONGITUDE")
    double longitude;
    @Column(name = "AZIMUT")
    double azimuth;
    @Column(name = "SPEED")
    double speed;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        return "TrackPoint{" +
                "id=" + id +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", azimuth=" + azimuth +
                ", speed=" + speed +
                '}';
    }


}
