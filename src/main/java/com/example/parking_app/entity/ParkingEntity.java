package com.example.parking_app.entity;

import com.example.parking_app.dto.ParkingDTO;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "parking")
public class ParkingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private boolean reservable; // Возможность брони

    @Column(nullable = false)
    private boolean subscribeable; // Возможность покупки абонемента

    @Column(nullable = false)
    private boolean temporary; // Возможность оплаты по времени

    @Column(nullable = false)
    private int capacity;

    // Связь с парковочными местами
    @OneToMany(mappedBy = "parking", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ParkingSpotEntity> parkingSpots;

    public ParkingEntity() {}

    public ParkingEntity(ParkingDTO parkingDTO) {
        this.id = parkingDTO.getId();
        this.address = parkingDTO.getAddress();
        this.reservable = parkingDTO.isReservable();
        this.subscribeable = parkingDTO.isSubscribeable();
        this.temporary = parkingDTO.isTemporary();
        this.capacity = parkingDTO.getCapacity();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isReservable() {
        return reservable;
    }

    public void setReservable(boolean reservable) {
        this.reservable = reservable;
    }

    public boolean isSubscribeable() {
        return subscribeable;
    }

    public void setSubscribeable(boolean subscribeable) {
        this.subscribeable = subscribeable;
    }

    public boolean isTemporary() {
        return temporary;
    }

    public void setTemporary(boolean temporary) {
        this.temporary = temporary;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public List<ParkingSpotEntity> getParkingSpots() {
        return parkingSpots;
    }

    public void setParkingSpots(List<ParkingSpotEntity> parkingSpots) {
        this.parkingSpots = parkingSpots;
    }
}
