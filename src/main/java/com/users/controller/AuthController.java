package com.users.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.users.dto.AuthenticationRequest;
import com.users.dto.AuthenticationResponse;
import com.users.dto.SignupRequest;
import com.users.dto.UserDto;
import com.users.entity.User;
import com.users.repository.UserRepository;
import com.users.services.auth.AuthService;
import com.users.services.jwt.UserService;
import com.users.util.JWTUtil;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

	@Autowired
	private AuthService authService;
	
	
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	
	@Autowired
	private UserService userService;
	
	@Autowired 
	private JWTUtil jwtUtil;
	
	@Autowired
	private UserRepository userRepository;
	
	
	@PostMapping("/signup")
	public ResponseEntity<?> signupCustomer(@RequestBody SignupRequest signupRequest){
		if(authService.hasUserWithEmail(signupRequest.getEmail()))
			return new ResponseEntity<>("Este customer ya existe", HttpStatus.NOT_ACCEPTABLE);
		
		UserDto createdUserDto = authService.createUser(signupRequest);
		
		
		
		if(createdUserDto == null) return new ResponseEntity<>
		( HttpStatus.CREATED);
		
		return new ResponseEntity<>(createdUserDto, HttpStatus.CREATED);
		
	}
	
	
	

	
	
	@PostMapping("/login")
	public AuthenticationResponse createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws
	       BadCredentialsException,
	       DisabledException,
	       UsernameNotFoundException {
		
		
		
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken
					(authenticationRequest.getEmail(), authenticationRequest.getPassword()));
			
		}catch(BadCredentialsException e) {
			throw new BadCredentialsException("Incorrecto password or email");
		}
		final UserDetails userDetails = userService.userDerailsService().loadUserByUsername(authenticationRequest.getEmail());
		Optional<User> optionalUser = userRepository.findFirstByEmail(userDetails.getUsername());
		final String jwt = jwtUtil.generateToken(userDetails);
		AuthenticationResponse authenticationResponse = new AuthenticationResponse();
		if(optionalUser.isPresent()) {
			authenticationResponse.setJwt(jwt);
			authenticationResponse.setUserId(optionalUser.get().getId());
			authenticationResponse.setUserRole(optionalUser.get().getUserRole());		
		}
		return authenticationResponse;
 
	}
	
	
	
	
	
	
	
	
	
	
	
    @GetMapping
    public List<User> getAllUsers() {
        return userService.findAll();
    }

    
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.findById(id);
        return user != null ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
    }

  
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return new ResponseEntity<>(userService.save(user), HttpStatus.CREATED);
    }

 
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userDetails) {
        User user = userService.findById(id);
        if (user != null) {
            user.setName(userDetails.getName());
            user.setEmail(userDetails.getEmail());
            return ResponseEntity.ok(userService.save(user));
        }
        return ResponseEntity.notFound().build();
    }

    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        if (userService.findById(id) != null) {
            userService.delete(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
	
	
}
