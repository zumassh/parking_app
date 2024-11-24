package com.example.parking_app.service;

import com.example.parking_app.entity.CarEntity;
import com.example.parking_app.entity.UserEntity;
import com.example.parking_app.exception.CarNotFoundException;
import com.example.parking_app.repository.CarRepo;
import com.example.parking_app.repository.UserRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
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
