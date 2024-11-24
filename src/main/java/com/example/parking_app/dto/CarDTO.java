package com.example.parking_app.dto;

import com.example.parking_app.entity.CarEntity;

public class CarDTO {
    Long id;
    String name;
    String number;
    String userNumber;

    public CarDTO(CarEntity car){
        id = car.getId();
        name = car.getName();
        number = car.getNumber();
        userNumber = car.getUser().getPhoneNumber();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(String userNumber) {
        this.userNumber = userNumber;
    }
}
