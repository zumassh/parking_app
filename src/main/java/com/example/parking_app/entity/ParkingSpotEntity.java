package com.example.parking_app.entity;

import com.example.parking_app.entity.CarEntity;
import com.example.parking_app.entity.ParkingEntity;
import com.example.parking_app.entity.ParkingSpotId;
import jakarta.persistence.*;

@Entity
@Table(name = "parking_spots")
@IdClass(ParkingSpotId.class) // Для составного первичного ключа
public class ParkingSpotEntity {

    @Id
    @Column(name = "parking_id", nullable = false)
    private Long parkingId;

    @Id
    @Column(name = "spot_number", nullable = false)
    private String spotNumber;

    @ManyToOne
    @JoinColumn(name = "parking_id", insertable = false, updatable = false)
    private ParkingEntity parking;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private CarEntity car;

    public ParkingSpotEntity(String spotNumber, Long id) {
        this.spotNumber = spotNumber;
        this.parkingId = id;
    }

    public ParkingSpotEntity() {}

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

    public ParkingEntity getParking() {
        return parking;
    }

    public void setParking(ParkingEntity parking) {
        this.parking = parking;
    }

    public CarEntity getCar() {
        return car;
    }

    public void setCar(CarEntity car) {
        this.car = car;
    }
}
