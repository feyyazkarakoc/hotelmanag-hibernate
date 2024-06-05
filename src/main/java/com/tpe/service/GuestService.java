package com.tpe.service;

import com.tpe.domain.Guest;
import com.tpe.exceptions.GuestNotFoundException;
import com.tpe.repository.GuestRepository;

import java.util.List;

public class GuestService {

    private final GuestRepository guestRepository;

    public GuestService(GuestRepository guestRepository) {
        this.guestRepository = guestRepository;
    }


    public Guest findGuestById(Long id) {
        try {
            Guest foundGuest = guestRepository.findById(id);
            if (foundGuest != null){
                System.out.println("---------------------------------------");
                System.out.println(foundGuest);
                System.out.println("---------------------------------------");
                return foundGuest;
            }else {
                throw new GuestNotFoundException("Guest not found by ID : "+id);
            }
        } catch (GuestNotFoundException e) {
            e.printStackTrace();
        }
        return null;

    }

    public void findAllGuest() {
        List<Guest> guests = guestRepository.findAll();
        if (!guests.isEmpty()){
            for (Guest guest : guests){
                System.out.println(guest);
            }
        }else {
            System.out.println("Guest list is empty...");
        }
    }
}
