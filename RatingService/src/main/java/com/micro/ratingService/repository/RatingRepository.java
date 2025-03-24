package com.micro.ratingService.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.micro.ratingService.entities.Rating;

public interface RatingRepository extends JpaRepository<Rating, String>{

	//custom finder method
	
	List<Rating> findByUserId(String userId);
	List<Rating> findByHotelId(String hotelId);
}
