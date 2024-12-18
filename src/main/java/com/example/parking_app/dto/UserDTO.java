package com.example.parking_app.dto;

import com.example.parking_app.entity.UserEntity;

public class UserDTO {
    Long id;
    String phoneNumber;

    public UserDTO(UserEntity user){
        this.id = user.getId();
        this.phoneNumber = user.getPhoneNumber();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
