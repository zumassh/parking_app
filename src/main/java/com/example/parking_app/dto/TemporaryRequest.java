package com.example.parking_app.dto;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class TemporaryRequest {
    CarDTO carDTO;
    ParkingDTO parkingDTO;
    LocalDateTime startTime;
    LocalDateTime endTime;
    int price;

    public TemporaryRequest(CarDTO carDTO, ParkingDTO parkingDTO, LocalDateTime startTime, LocalDateTime endTime, int price) {
        this.carDTO = carDTO;
        this.parkingDTO = parkingDTO;
        this.startTime = startTime;
        this.endTime = endTime;
        this.price = price;
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

    public CarDTO getCarDTO() {
        return carDTO;
    }

    public void setCarDTO(CarDTO carDTO) {
        this.carDTO = carDTO;
    }

    public ParkingDTO getParkingDTO() {
        return parkingDTO;
    }

    public void setParkingDTO(ParkingDTO parkingDTO) {
        this.parkingDTO = parkingDTO;
    }


    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "TemporaryRequest{" +
                "carDTO=" + carDTO +
                ", parkingDTO=" + parkingDTO +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", price=" + price +
                '}';
    }
}
