package com.example.parkovki_user_service.repository;

import com.example.parkovki_user_service.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<UserEntity, Long> {
    UserEntity findByPhoneNumber(String phoneNumber);
    void deleteByPhoneNumber(String phoneNumber);
}
