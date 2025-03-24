package com.micro.userservice.services.impl;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.micro.userservice.entities.Hotel;
import com.micro.userservice.entities.Rating;
import com.micro.userservice.entities.User;
import com.micro.userservice.exceptions.ResourceNotFoundException;
import com.micro.userservice.external.services.HotelService;
import com.micro.userservice.repositories.UserRepository;
import com.micro.userservice.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private HotelService hotelService;

	private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Override
	public User saveUser(User user) {
		String randomUserId = UUID.randomUUID().toString();
		user.setUserId(randomUserId);
		return userRepository.save(user);
	}

	@Override
	public List<User> getAllUser() {

		return userRepository.findAll();
	}

	// get single user

	@Override
	public User getUser(String userId) {
		// get user from database with the help of user repo
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found" + userId));

		// fetch rating of above user from RATING SERVICE
		
		Rating[] ratingOfUser = restTemplate.getForObject("http://RATING-SERVICE/rating/users/" + user.getUserId(),
				Rating[].class);
	//	logger.info("Rating of user{}", ratingOfUser);

		List<Rating> ratings = Arrays.stream(ratingOfUser).toList();

		List<Rating> ratingList = ratings.stream().map(rating -> {

			// using feign client
			Hotel hotel = hotelService.getHotel(rating.getHotelId());

			// logger.info("response status code" + forEntity.getStatusCodeValue());

			// set the hotel to rating
			rating.setHotel(hotel);
			// return the rating
			return rating;

		}).collect(Collectors.toList());

		user.setRating(ratingList);
		return user;
	}

}
