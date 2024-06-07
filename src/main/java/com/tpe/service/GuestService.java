package com.tpe.service;

import com.tpe.domain.Adress;
import com.tpe.domain.Guest;
import com.tpe.exceptions.GuestNotFoundException;
import com.tpe.repository.GuestRepository;

import java.util.List;
import java.util.Scanner;

public class GuestService {

    private Scanner scanner = new Scanner(System.in);

    private final GuestRepository guestRepository;

    public GuestService(GuestRepository guestRepository) {
        this.guestRepository = guestRepository;
    }


    public Guest findGuestById(Long id) {
        try {
            Guest foundGuest = guestRepository.findById(id);
            if (foundGuest != null) {
                System.out.println("---------------------------------------");
                System.out.println(foundGuest);
                System.out.println("---------------------------------------");
                return foundGuest;
            } else {
                throw new GuestNotFoundException("Guest not found by ID : " + id);
            }
        } catch (GuestNotFoundException e) {
            e.printStackTrace();
        }
        return null;

    }

    public void findAllGuest() {
        List<Guest> guests = guestRepository.findAll();
        if (!guests.isEmpty()) {
            for (Guest guest : guests) {
                System.out.println(guest);
            }
        } else {
            System.out.println("Guest list is empty...");
        }
    }

    public void saveGuest() {
        Guest guest = new Guest();

        System.out.println("Enter the guest name : ");
        guest.setName(scanner.nextLine());

        Adress adress = new Adress();

        System.out.println("Enter the street : ");
        adress.setStreet(scanner.nextLine());

        System.out.println("Enter the city : ");
        adress.setCity(scanner.nextLine());

        System.out.println("Enter the country : ");
        adress.setCountry(scanner.nextLine());

        System.out.println("Enter the zipcode : ");
        adress.setZipcode(scanner.nextInt());
        scanner.nextLine();

        guest.setAdress(adress);

        guestRepository.save(guest);
        System.out.println("Guest is saved successfully...");

    }

    public void deleteGuestById(Long id) {
        Guest foundGuest = findGuestById(id);
        if (foundGuest != null) {
            System.out.println("Are you sure to delete : ");
            System.out.println("Please answer with Y or N : ");
            String select = scanner.nextLine();
            if (select.equalsIgnoreCase("Y")) {
                guestRepository.delete(foundGuest);
                System.out.println("Guest is removed successfully...Guest ID : " + foundGuest.getId());
            } else {
                System.out.println("Delete operations is CANCELLED...");
            }
        }

    }
}
