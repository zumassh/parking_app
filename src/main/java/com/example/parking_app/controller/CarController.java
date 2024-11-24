package com.example.parking_app.controller;

import com.example.parking_app.entity.CarEntity;
import com.example.parking_app.exception.CarNotFoundException;
import com.example.parking_app.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cars")
public class CarController {
    @Autowired
    CarService carService;

    @PostMapping
    public ResponseEntity createCar(@RequestBody CarEntity car, @RequestParam Long userId){
        try{
            carService.createCar(car, userId);
            return ResponseEntity.ok("Автомобиль успешно добавлен.");
        }
        catch (Exception e){
            return  ResponseEntity.badRequest().body("Произошла ошибка: " + e.getMessage());
        }
    }

    @DeleteMapping
    public ResponseEntity deleteCarByNumber(@RequestParam String number) {
        try {
            return ResponseEntity.ok(carService.deleteCar(number));
        }
        catch (CarNotFoundException e){
            return  ResponseEntity.badRequest().body(e.getMessage());
        }
        catch (Exception e){
            return  ResponseEntity.badRequest().body("Произошла ошибка: " + e.getMessage());
        }
    }
}
