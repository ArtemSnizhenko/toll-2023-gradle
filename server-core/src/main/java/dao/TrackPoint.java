package dao;

import jdev.dto.Point;

import javax.persistence.*;

import static javax.persistence.GenerationType.AUTO;

@Entity
@Table(name="Tracks")
public class TrackPoint  extends Point {

    @Id
    @GeneratedValue(strategy = AUTO)
    @Column(name = "ID")
    int id;

    @Column(name = "TRACKERID")
    int trackerId;

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
