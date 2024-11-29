package com.example.parking_app.service;

import com.example.parking_app.entity.CarEntity;
import com.example.parking_app.entity.ParkingSpotEntity;
import com.example.parking_app.entity.ParkingSpotId;
import com.example.parking_app.entity.UserEntity;
import com.example.parking_app.exception.CarNotFoundException;
import com.example.parking_app.exception.NotEnoughMoneyException;
import com.example.parking_app.exception.ParkingSpotNotFoundException;
import com.example.parking_app.exception.SpotAlreadyNullException;
import com.example.parking_app.repository.CarRepo;
import com.example.parking_app.repository.ParkingSpotRepo;
import com.example.parking_app.repository.UserRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParkingSpotService {

    @Autowired
    private ParkingSpotRepo parkingSpotRepository;
    @Autowired
    private CarRepo carRepository;
    @Autowired
    private UserRepo userRepository;

    @Transactional
    public ParkingSpotEntity assignSpotToCar(Long parkingId, Long carId, Integer price) throws CarNotFoundException {
        ParkingSpotEntity spot = parkingSpotRepository.findFirstByParkingIdAndCarIsNull(parkingId)
                .orElseThrow(() -> new ParkingSpotNotFoundException("Нет свободных парковочных мест."));
        CarEntity car = carRepository.findById(carId)
                .orElseThrow(() -> new CarNotFoundException("Автомобиль не найден"));
        // здесь нужна проверка кошелька и списание
        UserEntity carOwner = userRepository.getUserEntityById(car.getUser().getId());
        if (carOwner.getWallet() - price < 0){
            throw new NotEnoughMoneyException("Не хватает денег на оплату парковки");
        }
        else {
            carOwner.setWallet(carOwner.getWallet() - price);
            userRepository.save(carOwner);
        }
        spot.setCar(car);
        spot = parkingSpotRepository.save(spot);
        return new ParkingSpotEntity(spot.getSpotNumber(), spot.getParking().getId());
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
