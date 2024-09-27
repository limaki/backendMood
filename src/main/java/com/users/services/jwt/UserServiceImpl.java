package com.users.services.jwt;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import com.users.dto.UserDto;
import com.users.entity.User;
import com.users.repository.UserRepository;

import lombok.RequiredArgsConstructor;

//obtener los detalles de usuario base de datos

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;
	
	

	@Override
	public UserDetailsService userDerailsService() {
		
		return new UserDetailsService() {
			
			@Override
			public UserDetails loadUserByUsername(String username) {
				return userRepository.findFirstByEmail(username)
						.orElseThrow(() -> new UsernameNotFoundException("User not Found"));
			}
		};
	}



	@Override
	public List<User> findAll() {
		
		return (List<User>) userRepository.findAll();
	}



	@Override
	public User save(User user) {
		
		return userRepository.save(user);
	}



	@Override
	public User findById(Long Id) {
		
		return userRepository.findById(Id).orElse(null);
	}



	@Override
	public void delete(Long Id) {
		userRepository.deleteById(Id);
		
	}
	




  
}