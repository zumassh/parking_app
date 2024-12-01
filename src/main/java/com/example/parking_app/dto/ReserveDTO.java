package com.example.parking_app.dto;

import java.time.LocalDateTime;

public class ReserveDTO {
    private Long id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private int price;
    private String reserveType;
    private ParkingSpotDTO parkingSpot;
    private CarDTO car;

    public ReserveDTO() {
    }

    public ReserveDTO(Long id, LocalDateTime startTime, LocalDateTime endTime, int price, String reserveType, ParkingSpotDTO parkingSpot, CarDTO car) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.price = price;
        this.reserveType = reserveType;
        this.parkingSpot = parkingSpot;
        this.car = car;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getReserveType() {
        return reserveType;
    }

    public void setReserveType(String reserveType) {
        this.reserveType = reserveType;
    }

    public ParkingSpotDTO getParkingSpot() {
        return parkingSpot;
    }

    public void setParkingSpot(ParkingSpotDTO parkingSpot) {
        this.parkingSpot = parkingSpot;
    }

    public CarDTO getCar() {
        return car;
    }

    public void setCar(CarDTO car) {
        this.car = car;
    }
}
