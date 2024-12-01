package com.example.parking_app.service;

import com.example.parking_app.dto.ReserveDTO;
import com.example.parking_app.dto.ReserveMapper;
import com.example.parking_app.entity.ReserveEntity;
import com.example.parking_app.repository.ReserveRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReserveService {

    @Autowired
    ReserveRepo reserveRepo;

    public List<ReserveDTO> getAllReserves(Long userId) {
        List<ReserveEntity> reserves = reserveRepo.findAllByCarUserId(userId);
        List<ReserveDTO> reserveDTOS = ReserveMapper.toDtoList(reserves);
        return reserveDTOS;
    }


    @Scheduled(fixedRate = 60000)
    public void handleExpiredReservations() {
        reserveRepo.freeExpiredParkingSpots(); // Освобождение мест
        reserveRepo.deleteExpiredReservations(); // Удаление истекших бронирований
        System.out.println("Обработаны истекшие бронирования");

    }

}
