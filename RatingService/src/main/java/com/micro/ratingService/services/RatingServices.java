package com.micro.ratingService.services;

import java.util.List;

import com.micro.ratingService.entities.Rating;

public interface RatingServices {

	//create
	Rating create(Rating rating);
	
	//get all ratings
	List<Rating> getRatings();
	
	//get all rating by userid
	List<Rating> getRatingByUserId(String userId);
	
	//get all by hotel
	List<Rating> getRatingByHotelId(String hotelId);
	
}
