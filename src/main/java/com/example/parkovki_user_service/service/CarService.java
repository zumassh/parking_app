package com.example.parkovki_user_service.service;

import com.example.parkovki_user_service.entity.CarEntity;
import com.example.parkovki_user_service.entity.UserEntity;
import com.example.parkovki_user_service.exception.CarNotFoundException;
import com.example.parkovki_user_service.exception.UserNotFoundException;
import com.example.parkovki_user_service.repository.CarRepo;
import com.example.parkovki_user_service.repository.UserRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CarService {
    @Autowired
    private CarRepo carRepo;
    @Autowired
    private UserRepo userRepo;

    public CarEntity createCar(CarEntity car, Long userId){
        UserEntity user = userRepo.findById(userId).get();
        car.setUser(user);
        return carRepo.save(car);
    }

    @Transactional
    public String deleteCar(String number) throws CarNotFoundException {
        CarEntity car = carRepo.findByNumber(number);
        if (car == null){
            throw new CarNotFoundException("Автомобиль с таким номером не найден.");
        }
        carRepo.deleteByNumber(number);
        return number;
    }
}
