package com.users.services.jwt;


import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.users.dto.UserDto;
import com.users.entity.User;



public interface UserService {

	UserDetailsService userDerailsService();
	
	public List<User> findAll();
	
	public User save(User user);
	
	public User findById(Long Id);
	
	public void delete(Long Id);

}
