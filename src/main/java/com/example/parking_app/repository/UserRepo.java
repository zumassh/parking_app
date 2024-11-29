package com.example.parking_app.repository;

import com.example.parking_app.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<UserEntity, Long> {
    UserEntity findByPhoneNumber(String phoneNumber);
    void deleteByPhoneNumber(String phoneNumber);

    UserEntity getUserEntityById(Long id);
}
