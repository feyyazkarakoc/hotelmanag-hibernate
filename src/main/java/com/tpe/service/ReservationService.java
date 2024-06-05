package com.tpe.service;

import com.tpe.domain.Reservation;
import com.tpe.exceptions.ReservationNotFoundException;
import com.tpe.repository.GuestRepository;
import com.tpe.repository.ReservationRepository;

public class ReservationService {

    private final ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }



    public Reservation findReservationById(Long id) {

        Reservation foundReservation = reservationRepository.findById(id);

        try {

            if (foundReservation != null){
                System.out.println("--------------------------------------");
                System.out.println(foundReservation);
                System.out.println("--------------------------------------");
                return foundReservation;
            }else {
                throw new ReservationNotFoundException("Reservation not found by ID : "+id);
            }
        } catch (ReservationNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return null;


    }
}
