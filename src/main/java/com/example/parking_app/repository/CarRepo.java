package com.example.parking_app.repository;

import com.example.parking_app.entity.CarEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CarRepo extends CrudRepository <CarEntity, Long> {
    List<CarEntity> findAllByUserId(Long userId);
    CarEntity findByNumber(String number);
    void deleteByNumber(String phoneNumber);
}
