package com.tpe.service;

import com.tpe.domain.Guest;
import com.tpe.domain.Reservation;
import com.tpe.domain.Room;
import com.tpe.exceptions.ReservationNotFoundException;
import com.tpe.repository.GuestRepository;
import com.tpe.repository.ReservationRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class ReservationService {

    private Scanner scanner = new Scanner(System.in);

    private final ReservationRepository reservationRepository;
    private final GuestService guestService;

    private final RoomService roomService;

    public ReservationService(ReservationRepository reservationRepository, GuestService guestService, RoomService roomService) {
        this.reservationRepository = reservationRepository;
        this.guestService = guestService;
        this.roomService = roomService;
    }

    public Reservation findReservationById(Long id) {

        Reservation foundReservation = reservationRepository.findById(id);

        try {

            if (foundReservation != null) {
                System.out.println("--------------------------------------");
                System.out.println(foundReservation);
                System.out.println("--------------------------------------");
                return foundReservation;
            } else {
                throw new ReservationNotFoundException("Reservation not found by ID : " + id);
            }
        } catch (ReservationNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return null;


    }

    public void findAllReservations() {
        List<Reservation> reservations = reservationRepository.findAll();

        if (!reservations.isEmpty()) {
            for (Reservation reservation : reservations) {
                System.out.println(reservation);
            }
        } else {
            System.out.println("There is no reservations...");
        }
    }

    public void createReservation() {

        Reservation reservation = new Reservation();


        //The validity of the entered dates was verified.
        System.out.println("Enter the check-in date (yyyy-MM-dd) : ");
        reservation.setCheckInDate(LocalDate.parse(scanner.nextLine()));

        System.out.println("Enter the check-out date (yyyy-MM-dd) : ");
        reservation.setCheckOutDate(LocalDate.parse(scanner.nextLine()));

        System.out.println("Enter the room ID : ");
        Long roomId = scanner.nextLong();
        scanner.nextLine();
        Room existingRoom = roomService.findRoomById(roomId);
        reservation.setRoom(existingRoom);

        System.out.println("Enter the guest ID : ");
        Long guestId = scanner.nextLong();
        scanner.nextLine();
        Guest existingGuest = guestService.findGuestById(guestId);
        reservation.setGuest(existingGuest);


        reservationRepository.save(reservation);
        System.out.println("Reservation is saved successfully...");


    }


    public void deleteReservationById(Long id) {
        Reservation existingReservation = findReservationById(id);

        if (existingReservation != null) {
            System.out.println("Are you sure to delete reservation : ");
            System.out.println("Please answer with Y or N : ");
            String select = scanner.nextLine();
            if (select.equalsIgnoreCase("Y")) {
                reservationRepository.delete(existingReservation);
                System.out.println("Reservation is removed successfully...Reservation ID : " + existingReservation.getId());
            } else {
                System.out.println("Delete operation is CANCELLED!!!");
            }
        }


    }
}
