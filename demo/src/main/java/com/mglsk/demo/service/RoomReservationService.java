package com.mglsk.demo.service;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mglsk.demo.data.entity.Guest;
import com.mglsk.demo.data.entity.Reservation;
import com.mglsk.demo.data.entity.Room;
import com.mglsk.demo.data.repository.GuestRepository;
import com.mglsk.demo.data.repository.ReservationRepository;
import com.mglsk.demo.data.repository.RoomRepository;
import com.mglsk.demo.service.model.RoomReservation;

import io.micrometer.common.util.StringUtils;

@Service
public class RoomReservationService {
    
    private final GuestRepository guestRepository;
    private final RoomRepository roomRepository;
    private final ReservationRepository reservationRepository;

    public RoomReservationService(GuestRepository guestRepository, RoomRepository roomRepository, ReservationRepository reservationRepository) {
        this.guestRepository = guestRepository;
        this.roomRepository = roomRepository;
        this.reservationRepository = reservationRepository;
    }

    public List<RoomReservation> getRoomReservationsForDate(String reservationDate){
        Date date = null;

        if(StringUtils.isNotEmpty(reservationDate)){
            date = Date.valueOf(reservationDate);
        } else {
            date = new Date(new java.util.Date().getTime());
        }

        Map<Long, RoomReservation> roomReservations = new HashMap<Long, RoomReservation>();

        List<Room> rooms = this.roomRepository.findAll();
        rooms.forEach( room -> {
            RoomReservation roomReservation = new RoomReservation();
            roomReservation.setRoomId(room.getId());
            roomReservation.setRoomName(room.getName());
            roomReservation.setRoomNumber(room.getRoomNumber());
            roomReservations.put(roomReservation.getRoomId(), roomReservation);
        });

        List<Reservation> reservations = this.reservationRepository.findAllByReservationDate(date);
        reservations.forEach( reservation -> {
            var roomReservation = roomReservations.get(reservation.getRoomId());
            roomReservation.setReservationId(reservation.getId());
            roomReservation.setReservationDate(reservation.getReservationDate().toString());
            
            Optional<Guest> guest = this.guestRepository.findById(reservation.getGuestId());
            roomReservation.setGuestId(guest.get().getId());
            roomReservation.setFirstName(guest.get().getFirstName());
            roomReservation.setLastName(guest.get().getLastName());
        });

        return roomReservations.values().stream().toList();
    }
}
