package com.tpe.exceptions;

public class ReservationNotFoundException extends RuntimeException {


    public ReservationNotFoundException(String message) {
        super(message);
    }
}
