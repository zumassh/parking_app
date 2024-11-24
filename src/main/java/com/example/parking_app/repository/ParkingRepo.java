package com.example.parking_app.repository;

import com.example.parking_app.entity.ParkingEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ParkingRepo extends CrudRepository<ParkingEntity, Long> {
    @Query("SELECT p FROM ParkingEntity p WHERE EXISTS (" +
            "SELECT ps FROM ParkingSpotEntity ps WHERE ps.parking = p AND ps.car IS NULL)")
    List<ParkingEntity> findAllWithFreeSpots();
    Optional<ParkingEntity> findById(Long id);
}
