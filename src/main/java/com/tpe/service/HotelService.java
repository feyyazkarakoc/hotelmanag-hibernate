package com.tpe.service;

import com.tpe.domain.Hotel;
import com.tpe.exceptions.HotelNotFoundException;
import com.tpe.repository.HotelRepository;

import java.util.List;
import java.util.Scanner;

public class HotelService {

    private Scanner scanner = new Scanner(System.in);
    private final HotelRepository hotelRepository;

    public HotelService(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }


    public void saveHotel() {
        Hotel hotel = new Hotel();

        System.out.println("Enter hotel ID :");
        hotel.setId(scanner.nextLong());
        scanner.nextLine();

        System.out.println("Enter hotel name :");
        hotel.setName(scanner.nextLine());

        System.out.println("Enter hotel location :");
        hotel.setLocation(scanner.nextLine());

        hotelRepository.save(hotel);

        System.out.println("Hotel is saved succesfully. Hotel ID : " + hotel.getId());

    }


    public Hotel findHotelById(Long id) {

        Hotel foundhotel = hotelRepository.findById(id);

        try {
            if (foundhotel != null) {

                System.out.println("-------------------------------------------");
                System.out.println(foundhotel);
                System.out.println("-------------------------------------------");
                return foundhotel;
            } else {
                throw new HotelNotFoundException("Hotel not found by ID : " + id);
            }
        } catch (HotelNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void getAllHotels() {
        List<Hotel> allHotels = hotelRepository.findAll();

        if (!allHotels.isEmpty()) {
            System.out.println("------------------ ALL HOTELS -----------------");
            for (Hotel hotel : allHotels) {
                System.out.println(hotel);
            }
            System.out.println("----------------------------------------------");
        } else {
            System.out.println("Hotel list is empty!");
        }

    }


    public void deleteHotel(Long hotelId) {

        Hotel foundHotel = findHotelById(hotelId);

        if (foundHotel != null){
            System.out.println(foundHotel);
            System.out.println("Are you sure to delete : ");
            System.out.println("Please answer with Y or N");
            String select = scanner.nextLine();

            if (select.equalsIgnoreCase("Y")){
                hotelRepository.delete(foundHotel);
                System.out.println("Hotel is deleted successfully...");
            }else {
                System.out.println("Delete operation is CANCELLED!!!");
            }
        }



    }

    public void updateHotelById(Long hotelId) {
        Hotel existingHotel = findHotelById(hotelId);

        if (existingHotel != null){
            System.out.println("Enter the new hotel name : ");
            existingHotel.setName(scanner.nextLine());
            System.out.println("Enter the new hotel location : ");
            existingHotel.setLocation(scanner.nextLine());
            hotelRepository.updateHotel(existingHotel);
            System.out.println("Hotel is updated successfully...");
        }
    }
}
