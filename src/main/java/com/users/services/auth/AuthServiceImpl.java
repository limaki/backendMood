package com.users.services.auth;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import com.users.dto.SignupRequest;
import com.users.dto.UserDto;
import com.users.entity.User;
import com.users.enums.UserRole;
import com.users.repository.UserRepository;
import com.users.services.auth.AuthService;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{
	
	@Autowired
	private UserRepository userRepository;
	
	//Comprobando si es admin o no
	
	//hay que iniciarlo y reiniciarlo para que se apliqueno los cambios y se agregue el admin
	
	@PostConstruct
	public void createAdminMethod() {
	User adminAccount = userRepository.findByUserRole(UserRole.ADMIN);
	if(adminAccount == null) {
			User newAdminAccount = new User();
			newAdminAccount.setName("Admin");
			newAdminAccount.setEmail("admin@test.com");
			newAdminAccount.setPassword(new BCryptPasswordEncoder().encode("admin"));
			newAdminAccount.setUserRole(UserRole.ADMIN);
			userRepository.save(newAdminAccount);
			System.out.println("Admin account created succesfully");
		}
		
	}
	
	
	@Override
	public UserDto createUser(SignupRequest signupRequest) {
		
		User user = new User(); //para que la entidad usuario puede crar u usuario en la base de datos
		user.setName(signupRequest.getName());
		user.setPassword(new BCryptPasswordEncoder().encode(signupRequest.getPassword()));
		user.setEmail(signupRequest.getEmail());
		user.setUserRole(UserRole.USER);
		User createdUser = userRepository.save(user);
		UserDto userDto = new UserDto();
		userDto.setId(createdUser.getId());
		userDto.setEmail(createdUser.getEmail());
		
		
		
		return null;
	}

	@Override
	public boolean hasUserWithEmail(String email) {
		
		return userRepository.findFirstByEmail(email).isPresent();
	}


	@Override
	public List<UserDto> getAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}



}

