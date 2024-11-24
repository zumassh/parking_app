package com.example.parking_app.entity;

import java.io.Serializable;
import java.util.Objects;

public class ParkingSpotId implements Serializable {

    private Long parkingId;
    private String spotNumber;


    public ParkingSpotId() {}

    public ParkingSpotId(Long parkingId, String spotNumber) {
        this.parkingId = parkingId;
        this.spotNumber = spotNumber;
    }

    public Long getParkingId() {
        return parkingId;
    }

    public void setParkingId(Long parkingId) {
        this.parkingId = parkingId;
    }

    public String getSpotNumber() {
        return spotNumber;
    }

    public void setSpotNumber(String spotNumber) {
        this.spotNumber = spotNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParkingSpotId that = (ParkingSpotId) o;
        return Objects.equals(parkingId, that.parkingId) && Objects.equals(spotNumber, that.spotNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(parkingId, spotNumber);
    }
}
