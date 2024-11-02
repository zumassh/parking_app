package com.example.parkovki_user_service.service;

import com.example.parkovki_user_service.DTO.CarDTO;
import com.example.parkovki_user_service.DTO.UserDTO;
import com.example.parkovki_user_service.entity.CarEntity;
import com.example.parkovki_user_service.entity.UserEntity;
import com.example.parkovki_user_service.exception.UserAlreadyExistException;
import com.example.parkovki_user_service.exception.UserNotFoundException;
import com.example.parkovki_user_service.repository.CarRepo;
import com.example.parkovki_user_service.repository.UserRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private CarRepo carRepo;

    public UserEntity registration(UserEntity user) throws UserAlreadyExistException {
        if (userRepo.findByPhoneNumber(user.getPhoneNumber()) != null){
            throw new UserAlreadyExistException("Пользователь с таким номером уже зарегистрирован в системе.");
        }
        return userRepo.save(user);
    }

    public UserDTO getByPhone(String phoneNumber) throws UserNotFoundException {
        UserEntity user = userRepo.findByPhoneNumber(phoneNumber);
        if (user == null){
            throw new UserNotFoundException("Пользователь с таким номером не найден.");
        }
        return new UserDTO(user);
    }

    @Transactional
    public String deleteUser(String phoneNumber) throws UserNotFoundException {
        UserEntity user = userRepo.findByPhoneNumber(phoneNumber);
        if (user == null){
            throw new UserNotFoundException("Пользователь с таким номером не найден.");
        }
        userRepo.deleteByPhoneNumber(phoneNumber);
        return phoneNumber;
    }

    public List<CarDTO> getCarsByUserId(Long userId) throws UserNotFoundException {
        if (!userRepo.existsById(userId)) {
            throw new UserNotFoundException("Пользователь не найден.");
        }
        return carRepo.findAllByUserId(userId).stream()
                .map(car -> new CarDTO(car)).collect(Collectors.toList());
    }
}
