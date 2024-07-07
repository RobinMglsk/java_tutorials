package com.mglsk.demo.data.repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mglsk.demo.data.entity.Reservation;

public interface ReservationRepository  extends JpaRepository<Reservation, Long>{
    Optional<Reservation> findByReservationDate(Date resDate);
    List<Reservation> findAllByReservationDate(Date resDate);
}