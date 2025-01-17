package com.mglsk.demo.data.entity;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.ToString;

@Entity
@Data
@ToString
@Table(name = "reservations")
public class Reservation {
    @Id
    @Column(name = "reservation_id")
    private long id;

    @Column(name = "room_id")
    private long roomId;

    @Column(name = "guest_id")
    private long guestId;

    @Column(name = "res_date")
    private Date reservationDate;
}
