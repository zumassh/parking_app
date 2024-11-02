package com.example.parkovki_user_service.repository;

import com.example.parkovki_user_service.entity.CarEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.List;

public interface CarRepo extends CrudRepository <CarEntity, Long> {
    List<CarEntity> findAllByUserId(Long userId);
    CarEntity findByNumber(String number);
    void deleteByNumber(String phoneNumber);
}
