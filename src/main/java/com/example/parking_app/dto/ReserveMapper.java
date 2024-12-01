package com.example.parking_app.dto;


import com.example.parking_app.entity.ReserveEntity;

import java.util.List;
import java.util.stream.Collectors;

public class ReserveMapper {

    public static ReserveDTO toDto(ReserveEntity reserveEntity) {
        if (reserveEntity == null) {
            return null;
        }

        ParkingSpotDTO parkingSpotDTO = new ParkingSpotDTO(
                reserveEntity.getParkingSpot().getParkingId(),
                reserveEntity.getParkingSpot().getSpotNumber()
        );

        CarDTO carDTO = null;
        if (reserveEntity.getParkingSpot().getCar() != null) {
            carDTO = new CarDTO(
                    reserveEntity.getParkingSpot().getCar().getId(),
                    reserveEntity.getParkingSpot().getCar().getName(),
                    reserveEntity.getParkingSpot().getCar().getNumber(),
                    reserveEntity.getParkingSpot().getCar().getUser().getPhoneNumber()
            );
        }

        return new ReserveDTO(
                reserveEntity.getId(),
                reserveEntity.getStartTime(),
                reserveEntity.getEndTime(),
                reserveEntity.getPrice(),
                reserveEntity.getReserveType(),
                parkingSpotDTO,
                reserveEntity.getParkingSpot().getParking().getAddress(),
                carDTO
        );
    }

    public static List<ReserveDTO> toDtoList(List<ReserveEntity> reserveEntities) {
        return reserveEntities.stream()
                .map(ReserveMapper::toDto)
                .collect(Collectors.toList());
    }
}
