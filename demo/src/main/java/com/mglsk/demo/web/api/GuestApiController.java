package com.mglsk.demo.web.api;

import org.springframework.web.bind.annotation.RestController;

import com.mglsk.demo.data.entity.Guest;
import com.mglsk.demo.data.repository.GuestRepository;
import com.mglsk.demo.web.exception.BadRequestException;
import com.mglsk.demo.web.exception.NotFoundException;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/guests")
public class GuestApiController {

    private final GuestRepository guestRepository;

    public GuestApiController(GuestRepository guestRepository) {
        this.guestRepository = guestRepository;
    }

    @GetMapping
    public List<Guest> getAllGuests() {
        return this.guestRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Guest createGuest(@RequestBody Guest guest) {
        return this.guestRepository.save(guest);
    }

    @GetMapping("/{id}")
    public Guest getGuest(@PathVariable("id") long id) {
        Optional<Guest> guest = this.guestRepository.findById(id);

        if (guest.isEmpty()) {
            throw new NotFoundException("Guest not found with id: " + id);
        }

        return guest.get();
    }

    @PutMapping("/{id}")
    public Guest updateGuest(@PathVariable("id") long id, @RequestBody Guest guest) {
        if (id != guest.getId()) {
            throw new BadRequestException("id on path does not match body");
        }

        return this.guestRepository.save(guest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.RESET_CONTENT)
    public void deleteRoom(@PathVariable("id") long id){
        this.guestRepository.deleteById(id);
    }

}
