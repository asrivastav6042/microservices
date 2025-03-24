package com.micro.hotelService.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.micro.hotelService.entities.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, String>{

}
