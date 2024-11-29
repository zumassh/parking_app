package com.example.parking_app.controller;

import com.example.parking_app.entity.ParkingSpotEntity;
import com.example.parking_app.exception.CarNotFoundException;
import com.example.parking_app.service.ParkingSpotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/parking-spots")
public class ParkingSpotController {
    @Autowired
    ParkingSpotService parkingSpotService;

    @PostMapping("/{parkingId}/assign")
    public ResponseEntity<String> assignSpot(@PathVariable Long parkingId, @RequestParam Long carId, @RequestParam Integer price) throws CarNotFoundException {
        ParkingSpotEntity spot = parkingSpotService.assignSpotToCar(parkingId, carId, price);

        String message = "Машиноместо подтверждено. Ваше парковочное место: " + spot.getSpotNumber();
        return ResponseEntity.ok(message);
    }

    @PostMapping("/{parkingId}/{spotNumber}/unassign")
    public ResponseEntity<String> unassignCarFromSpot(@PathVariable Long parkingId, @PathVariable String spotNumber) {
        try {
            ParkingSpotEntity spot = parkingSpotService.unassignCarFromSpot(parkingId, spotNumber);
            String message = "Машина была успешно удалена с парковочного места: " +
                    spot.getSpotNumber();
            return ResponseEntity.ok(message);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка: " + e.getMessage());
        }

    }

}
