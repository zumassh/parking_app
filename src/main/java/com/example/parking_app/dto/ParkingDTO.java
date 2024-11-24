package com.example.parking_app.dto;

import com.example.parking_app.entity.ParkingEntity;
import jakarta.persistence.Column;

public class ParkingDTO {
    Long id;
    String address;
    boolean reservable; // Возможность брони
    boolean subscribeable; // Возможность покупки абонемента
    boolean temporary; // Возможность оплаты по времени
    int capacity;

    public ParkingDTO(ParkingEntity parking) {
        this.capacity = parking.getCapacity();
        this.temporary = parking.isTemporary();
        this.subscribeable = parking.isSubscribeable();
        this.reservable = parking.isReservable();
        this.address = parking.getAddress();
        this.id = parking.getId();
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
}
