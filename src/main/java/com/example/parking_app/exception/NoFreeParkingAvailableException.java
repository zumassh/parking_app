package com.example.parking_app.exception;

public class NoFreeParkingAvailableException extends RuntimeException {
    public NoFreeParkingAvailableException(String message) {
        super(message);
    }
}
