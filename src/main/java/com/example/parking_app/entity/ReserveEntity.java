package com.example.parking_app.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "reserves")
public class ReserveEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "start_time", nullable = false)
    private LocalDateTime startTime;

    @Column(name = "end_time", nullable = false)
    private LocalDateTime endTime;

    @OneToOne
    @JoinColumns({
            @JoinColumn(name = "parking_spots_id", referencedColumnName = "parking_id"),
            @JoinColumn(name = "spot_number", referencedColumnName = "spot_number")
    })
    private ParkingSpotEntity parkingSpot;

    private int price;

    @Column(name = "reserve_type")
    private String reserveType;

    public ReserveEntity() {
    }

    public ReserveEntity(Long id, LocalDateTime startTime, LocalDateTime endTime, ParkingSpotEntity parkingSpot, int price, String reserveType) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.parkingSpot = parkingSpot;
        this.price = price;
        this.reserveType = reserveType;
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

    public ParkingSpotEntity getParkingSpot() {
        return parkingSpot;
    }

    public void setParkingSpot(ParkingSpotEntity parkingSpot) {
        this.parkingSpot = parkingSpot;
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
}
