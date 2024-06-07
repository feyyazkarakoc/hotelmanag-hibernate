package com.tpe.service;

import com.tpe.domain.Hotel;
import com.tpe.domain.Room;
import com.tpe.exceptions.RoomNotFoundException;
import com.tpe.repository.RoomRepository;

import java.util.List;
import java.util.Scanner;

public class RoomService {

    private Scanner scanner = new Scanner(System.in);
    private final HotelService hotelService;

    private final RoomRepository roomRepository;

    public RoomService(HotelService hotelService, RoomRepository roomRepository) {
        this.hotelService = hotelService;
        this.roomRepository = roomRepository;
    }

    public void saveRoom() {

        Room room = new Room();

        System.out.println("Enter room Id : ");
        room.setId(scanner.nextLong());
        scanner.nextLine();

        System.out.println("Enter room number : ");
        room.setNumber(scanner.nextLine());

        System.out.println("Enter room capasity : ");
        room.setCapasity(scanner.nextInt());
        scanner.nextLine();


        System.out.println("Enter hotel id : ");
        Long hotelId = scanner.nextLong();
        scanner.nextLine();

        Hotel foundHotel = hotelService.findHotelById(hotelId);
        if (foundHotel != null) {

            room.setHotel(foundHotel);
            //foundHotel.getRooms().add(room); !!!mappedBy

            roomRepository.save(room);

            System.out.println("Room is saved successfully.Room id : " + room.getId());

        } else {
            System.out.println("Room could not saved!!!");
        }

    }

    public Room findRoomById(Long roomId) {

        try {
            Room foundRoom = roomRepository.findById(roomId);
            if (foundRoom != null) {
                System.out.println("---------------------------------------");
                System.out.println(foundRoom);
                System.out.println("---------------------------------------");
                return foundRoom;
            } else {
                throw new RoomNotFoundException("Room found not by ID : " + roomId);
            }
        } catch (RoomNotFoundException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public void getAllRooms() {

        List<Room> roomList = roomRepository.getAll();
        if (!roomList.isEmpty()) {
            System.out.println("------------------ ALL ROOMS ------------------");
            for (Room room : roomList) {
                System.out.println(room);
            }
            System.out.println("-----------------------------------------------");
        } else {
            System.out.println("Room list is empty.");
        }
    }

    public void deleteRoomById(Long id) {
        Room existingRoom = findRoomById(id);
        if (existingRoom != null) {
            System.out.println(existingRoom);
            System.out.println("Are you sure to delete : ");
            System.out.println("Please answer with Y or N");
            String select = scanner.nextLine();

            if (select.equalsIgnoreCase("Y")) {
                roomRepository.deleteRoom(existingRoom);
                System.out.println("Room is deleted succesfully...");
            } else {
                System.out.println("Delete operation is CANCELLED!!!");
            }
        }

    }
}
