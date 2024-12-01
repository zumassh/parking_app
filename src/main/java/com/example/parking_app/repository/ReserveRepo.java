package com.example.parking_app.repository;

import com.example.parking_app.entity.ReserveEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReserveRepo extends CrudRepository<ReserveEntity, Long> {
    @Query("SELECT r FROM ReserveEntity r " +
            "JOIN r.parkingSpot ps " +
            "JOIN ps.car c " +
            "WHERE c.user.id = :userId")
    List<ReserveEntity> findAllByCarUserId(@Param("userId") Long userId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE parking_spots " +
            "SET car_id = NULL " +
            "WHERE spot_number IN (SELECT spot_number FROM reserves WHERE end_time < UTC_TIMESTAMP())",
            nativeQuery = true)
    void freeExpiredParkingSpots();

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM reserves WHERE end_time < UTC_TIMESTAMP()", nativeQuery = true)
    void deleteExpiredReservations();

}
