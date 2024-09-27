package com.users.services.auth;


import java.util.List;

import com.users.dto.SignupRequest;
import com.users.dto.UserDto;


public interface AuthService {

	UserDto createUser(SignupRequest signupRequest);
	
	boolean hasUserWithEmail(String email);
	
	//devolver usuarios
	List<UserDto> getAllUsers();
	

	
}
