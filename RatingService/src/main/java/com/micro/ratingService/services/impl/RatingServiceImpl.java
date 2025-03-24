package com.micro.ratingService.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.micro.ratingService.entities.Rating;
import com.micro.ratingService.repository.RatingRepository;
import com.micro.ratingService.services.RatingServices;

@Service
public class RatingServiceImpl implements RatingServices {

	@Autowired
	private RatingRepository ratingRepository;

	@Override
	public Rating create(Rating rating) {

		String id = UUID.randomUUID().toString();
		rating.setRatingId(id);

		return ratingRepository.save(rating);
	}

	@Override
	public List<Rating> getRatings() {
		return ratingRepository.findAll();
	}

	@Override
	public List<Rating> getRatingByUserId(String userId) {
		return ratingRepository.findByUserId(userId);
	}

	@Override
	public List<Rating> getRatingByHotelId(String hotelId) {
		return ratingRepository.findByHotelId(hotelId);
	}

}
