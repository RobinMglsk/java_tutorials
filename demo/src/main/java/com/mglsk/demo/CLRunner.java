package com.mglsk.demo;

import java.util.List;
import java.util.Optional;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.mglsk.demo.data.entity.Guest;
import com.mglsk.demo.data.entity.Reservation;
import com.mglsk.demo.data.entity.Room;
import com.mglsk.demo.data.repository.GuestRepository;
import com.mglsk.demo.data.repository.ReservationRepository;
import com.mglsk.demo.data.repository.RoomRepository;
import com.mglsk.demo.service.RoomReservationService;
import com.mglsk.demo.service.model.RoomReservation;

@Component
public class CLRunner implements CommandLineRunner {

    private final RoomRepository roomRepository;
    private final GuestRepository guestRespository;
    private final ReservationRepository reservationRepository;
    private final RoomReservationService roomReservationService;

    public CLRunner( RoomRepository roomRepository,  GuestRepository guestRespository,  ReservationRepository reservationRepository, RoomReservationService roomReservationService) {
        this.roomRepository = roomRepository;
        this.guestRespository = guestRespository;
        this.reservationRepository = reservationRepository;
        this.roomReservationService = roomReservationService;
    }

    @Override
    public void run(String... args) throws Exception {
        // List<Room> rooms = this.roomRepository.findAll();
        // List<Guest> guests = this.guestRespository.findAll();
        // List<Reservation> reservations = this.reservationRepository.findAll();
        // Optional<Room> room = this.roomRepository.findByRoomNumberIgnoreCase("p1");

        // System.out.println(room);

        // rooms.forEach(System.out::println);
        // guests.forEach(System.out::println);
        // reservations.forEach(System.out::println);

        List<RoomReservation> r = this.roomReservationService.getRoomReservationsForDate("2023-08-28");
        r.forEach(System.out::println);
    }
    
}
