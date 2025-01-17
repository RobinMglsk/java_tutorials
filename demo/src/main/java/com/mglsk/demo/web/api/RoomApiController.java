package com.mglsk.demo.web.api;

import org.springframework.web.bind.annotation.RestController;

import com.mglsk.demo.data.entity.Room;
import com.mglsk.demo.data.repository.RoomRepository;
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
@RequestMapping("/api/rooms")
public class RoomApiController {

    private final RoomRepository roomRepository;

    public RoomApiController(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @GetMapping
    public List<Room> getAllRooms() {
        return this.roomRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Room createRoom(@RequestBody Room room) {
        return this.roomRepository.save(room);
    }

    @GetMapping("/{id}")
    public Room getRoom(@PathVariable("id") long id) {
        Optional<Room> room = this.roomRepository.findById(id);

        if (room.isEmpty()) {
            throw new NotFoundException("Room not found with id: " + id);
        }

        return room.get();
    }

    @PutMapping("/{id}")
    public Room updateRoom(@PathVariable("id") long id, @RequestBody Room room) {
        if (id != room.getId()) {
            throw new BadRequestException("id on path does not match body");
        }

        return this.roomRepository.save(room);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.RESET_CONTENT)
    public void deleteRoom(@PathVariable("id") long id){
        this.roomRepository.deleteById(id);
    }

}
