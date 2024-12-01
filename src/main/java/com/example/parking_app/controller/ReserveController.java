package com.example.parking_app.controller;

import com.example.parking_app.dto.ReserveDTO;
import com.example.parking_app.entity.ParkingSpotEntity;
import com.example.parking_app.entity.ReserveEntity;
import com.example.parking_app.repository.ParkingSpotRepo;
import com.example.parking_app.repository.ReserveRepo;
import com.example.parking_app.service.ReserveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reserves")
public class ReserveController {

    @Autowired
    ReserveService reserveService;


    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ReserveDTO>> getReservesByUserId(@PathVariable Long userId) {
        List<ReserveDTO> reserveDTOs = reserveService.getAllReserves(userId);
        return ResponseEntity.ok(reserveDTOs);
    }
}
