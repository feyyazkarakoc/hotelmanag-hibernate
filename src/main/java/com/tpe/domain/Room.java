package com.tpe.domain;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "t_room")
public class Room {

    @Id
    private Long id;

    @Column(nullable = false)
    private String number;


    @Column(nullable = false)
    private  Integer capasity;


    @ManyToOne
    @JoinColumn(name = "hotel_id",nullable = false)
    private Hotel hotel;

    private List<Reservation> reservations = new ArrayList<>();

    public Room() {
    }

    public Room(Long id, String number, Integer capasity, Hotel hotel) {
        this.id = id;
        this.number = number;
        this.capasity = capasity;
        this.hotel = hotel;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Integer getCapasity() {
        return capasity;
    }

    public void setCapasity(Integer capasity) {
        this.capasity = capasity;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", capasity=" + capasity +
                '}';
    }
}
