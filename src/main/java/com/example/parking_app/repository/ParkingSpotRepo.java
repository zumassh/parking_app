package com.example.parking_app.repository;

import com.example.parking_app.entity.ParkingSpotEntity;
import com.example.parking_app.entity.ParkingSpotId;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ParkingSpotRepo extends CrudRepository<ParkingSpotEntity, ParkingSpotId> {
    Optional<ParkingSpotEntity> findFirstByParkingIdAndCarIsNull(Long parkingId);
    Optional<ParkingSpotEntity> findById(ParkingSpotId id);
}
