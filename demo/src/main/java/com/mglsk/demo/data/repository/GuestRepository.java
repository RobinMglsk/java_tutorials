package com.mglsk.demo.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mglsk.demo.data.entity.Guest;

public interface GuestRepository  extends JpaRepository<Guest, Long>{
}