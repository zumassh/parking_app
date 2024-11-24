package com.example.parking_app.service;

import com.example.parking_app.dto.ParkingDTO;
import com.example.parking_app.entity.ParkingEntity;
import com.example.parking_app.entity.ParkingSpotEntity;
import com.example.parking_app.exception.CarNotFoundException;
import com.example.parking_app.exception.NoFreeParkingAvailableException;
import com.example.parking_app.exception.ParkingNotFoundException;
import com.example.parking_app.repository.ParkingRepo;
import com.example.parking_app.repository.ParkingSpotRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ParkingService {
    @Autowired
    private ParkingRepo parkingRepo;
    @Autowired
    private ParkingSpotRepo parkingSpotRepo;

    public ParkingDTO getParkingById(Long id) {
        ParkingEntity parking = parkingRepo.findById(id)
                .orElseThrow(() -> new ParkingNotFoundException("Парковка с таким номером не найдена"));
        return new ParkingDTO(parking);
    }

    public ParkingDTO createParking(ParkingDTO parkingDTO) {
        ParkingEntity parking = new ParkingEntity(parkingDTO);
        parking = parkingRepo.save(parking);
        for (int i = 1; i <= parkingDTO.getCapacity(); i++) {
            String spotNumber = "P-" + i;
            ParkingSpotEntity spot = new ParkingSpotEntity(spotNumber, parking.getId());
            parkingSpotRepo.save(spot);
        }
        return new ParkingDTO(parking);
    }

    @Transactional
    public String deleteParking(Long id) {
        Optional<ParkingEntity> parking = parkingRepo.findById(id);
        if (parking.isEmpty()){
            throw new ParkingNotFoundException("Парковка с таким номером не найдена.");
        }
        parkingRepo.deleteById(id);
        return "Парковка с id " + id + " удалена.";
    }

    public List<ParkingDTO> getAllFreeParkings() {
        List<ParkingEntity> freeParkings = parkingRepo.findAllWithFreeSpots();
        if (freeParkings.isEmpty()) {
            throw new NoFreeParkingAvailableException("К сожалению, сейчас нет доступных парковок.");
        }
        return freeParkings.stream()
                .map(ParkingDTO::new)
                .collect(Collectors.toList());
    }
}
