package com.example.parking_app.controller;

import com.example.parking_app.dto.ParkingDTO;
import com.example.parking_app.entity.ParkingEntity;
import com.example.parking_app.exception.CarNotFoundException;
import com.example.parking_app.exception.NoFreeParkingAvailableException;
import com.example.parking_app.exception.ParkingNotFoundException;
import com.example.parking_app.exception.UserNotFoundException;
import com.example.parking_app.service.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parking")
public class ParkingController {
    @Autowired
    private ParkingService parkingService;

    @PostMapping
    public ResponseEntity createParking(@RequestBody ParkingEntity parking){
        try {
            parkingService.createParking(new ParkingDTO(parking));
            return ResponseEntity.ok("Парковка успешно добавлена.");
        }
        catch (Exception e) {
            return  ResponseEntity.badRequest().body("Произошла ошибка: " + e.getMessage());
        }
    }

    @DeleteMapping
    public ResponseEntity deleteParkingById(@RequestParam Long id) {
        try {
            return ResponseEntity.ok(parkingService.deleteParking(id));
        }
        catch (ParkingNotFoundException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка: " + e.getMessage());
        }
    }

    @GetMapping("/free")
    public ResponseEntity<?> getAllFreeParkings() {
        try {
            List<ParkingDTO> freeParkings = parkingService.getAllFreeParkings();
            return ResponseEntity.ok(freeParkings);
        } catch (NoFreeParkingAvailableException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity getParkingById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(parkingService.getParkingById(id));
        } catch (ParkingNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка: " + e.getMessage());
        }
    }

}
