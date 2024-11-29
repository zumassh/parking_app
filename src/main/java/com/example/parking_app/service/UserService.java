package com.example.parking_app.service;

import com.example.parking_app.dto.CarDTO;
import com.example.parking_app.dto.UserDTO;
import com.example.parking_app.entity.UserEntity;
import com.example.parking_app.exception.UserAlreadyExistException;
import com.example.parking_app.exception.UserNotFoundException;
import com.example.parking_app.repository.CarRepo;
import com.example.parking_app.repository.UserRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
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

    public UserEntity login(String phoneNumber, String password) throws UserNotFoundException {
        UserEntity user = userRepo.findByPhoneNumber(phoneNumber);
        if (user == null){
            throw new UserNotFoundException("Пользователь не найден");
        }
        else {
            if (!Objects.equals(password, user.getPassword())) {
                throw new IllegalArgumentException("Неверный пароль");
            }
            return user;
        }
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
                .map(CarDTO::new).collect(Collectors.toList());
    }

    @Transactional
    public String addMoneyToWallet(String phoneNumber, Integer money) throws UserNotFoundException {
        UserEntity user = userRepo.findByPhoneNumber(phoneNumber);
        if (user == null){
            throw new UserNotFoundException("Пользователь с таким номером не найден.");
        }
        user.setWallet(user.getWallet() + money);
        userRepo.save(user);
        return "Пополнение прошло успешно.";
    }
}
