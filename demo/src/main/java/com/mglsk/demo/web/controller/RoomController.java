package com.mglsk.demo.web.controller;

import java.sql.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mglsk.demo.data.repository.RoomRepository;
import com.mglsk.demo.service.RoomReservationService;

import io.micrometer.common.util.StringUtils;

import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/rooms")
public class RoomController {
    
    private final RoomRepository roomRepository;
    private final RoomReservationService roomReservationService;

    public RoomController(RoomRepository roomRepository, RoomReservationService roomReservationService) {
        this.roomRepository = roomRepository;
        this.roomReservationService = roomReservationService;
    }

    @GetMapping
    public String getRooms(Model model){
        model.addAttribute("rooms", this.roomRepository.findAll());
        return "room-list";
    }

    @GetMapping("/reservations")
    public String requestMethodName(@RequestParam(value = "date", required = false) String date, Model model) {

        if(StringUtils.isEmpty(date)){
            date = new Date(new java.util.Date().getTime()).toString();
        } 

        model.addAttribute("rooms", this.roomReservationService.getRoomReservationsForDate(date));
        model.addAttribute("date", date);
        return "room-reservation-list";
    }
    

}
