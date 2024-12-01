package com.example.parking_app.service;

import com.example.parking_app.dto.TemporaryRequest;
import com.example.parking_app.entity.*;
import com.example.parking_app.exception.CarNotFoundException;
import com.example.parking_app.exception.NotEnoughMoneyException;
import com.example.parking_app.exception.ParkingSpotNotFoundException;
import com.example.parking_app.exception.SpotAlreadyNullException;
import com.example.parking_app.repository.CarRepo;
import com.example.parking_app.repository.ParkingSpotRepo;
import com.example.parking_app.repository.ReserveRepo;
import com.example.parking_app.repository.UserRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Service
public class ParkingSpotService {

    @Autowired
    private ParkingSpotRepo parkingSpotRepository;
    @Autowired
    private CarRepo carRepository;
    @Autowired
    private UserRepo userRepository;
    @Autowired
    private ReserveRepo reserveRepository;



    public ParkingSpotEntity assignSpotToCar(TemporaryRequest request, String type) {
        if (type.equals("Подписка")) {
            ZonedDateTime nowUTC = ZonedDateTime.now(ZoneId.of("UTC")); // Текущий момент по UTC
            ZonedDateTime oneMonthLaterUTC = nowUTC.plusMonths(1); // Момент через месяц по UTC

            request.setStartTime(nowUTC.toLocalDateTime()); // Преобразуем ZonedDateTime в LocalDateTime
            request.setEndTime(oneMonthLaterUTC.toLocalDateTime());
        }
        ParkingSpotEntity spot = parkingSpotRepository.findFirstByParkingIdAndCarIsNull(request.getParkingDTO().getId())
                .orElseThrow(() -> new ParkingSpotNotFoundException("Нет свободных парковочных мест."));
        CarEntity car = carRepository.findById(request.getCarDTO().getId())
                .orElseThrow(() -> new CarNotFoundException("Автомобиль не найден"));
        UserEntity carOwner = userRepository.getUserEntityById(car.getUser().getId());
        if (carOwner.getWallet() - request.getPrice() < 0){
            throw new NotEnoughMoneyException("Не хватает денег на оплату парковки");
        }

        carOwner.setWallet(carOwner.getWallet() - request.getPrice());
        userRepository.save(carOwner);

        spot.setCar(car);
        spot = parkingSpotRepository.save(spot);

        ReserveEntity reserveEntity = new ReserveEntity();
        reserveEntity.setStartTime(request.getStartTime());
        reserveEntity.setEndTime(request.getEndTime());
        reserveEntity.setPrice(request.getPrice());
        reserveEntity.setReserveType(type);
        reserveEntity.setParkingSpot(spot);

        reserveRepository.save(reserveEntity);

        return spot;
    }





    public ParkingSpotEntity unassignCarFromSpot(Long parkingId, String spotNumber) {

        ParkingSpotEntity spot = parkingSpotRepository.findById(new ParkingSpotId(parkingId, spotNumber))
                .orElseThrow(() -> new ParkingSpotNotFoundException("Парковочное место не найдено"));
        if (spot.getCar() == null){
            throw new SpotAlreadyNullException("Это машиноместо уже свободно.");
        }
        spot.setCar(null);
        parkingSpotRepository.save(spot);
        return spot;
    }
}
