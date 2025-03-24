package com.micro.hotelService.service;

import java.util.List;

import com.micro.hotelService.entities.Hotel;

public interface HotelService {
	
	
	//create
	Hotel createHotel(Hotel hotel);
	//all hotel
	List<Hotel> getall();
	
	//single hotel
	Hotel get(String id);
}
