package com.micro.userservice.services;

import java.util.List;

import com.micro.userservice.entities.User;

public interface UserService {
	
	//create 
	User saveUser(User user);
	
	//get all user
	List<User> getAllUser();
	
	//get single user
	User getUser(String userId);
	
	

}
