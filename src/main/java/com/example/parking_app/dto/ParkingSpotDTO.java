package com.example.parking_app.dto;

public class ParkingSpotDTO {
    private Long parkingId;
    private String spotNumber;

    public ParkingSpotDTO() {
    }

    public ParkingSpotDTO(Long parkingId, String spotNumber) {
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
}
