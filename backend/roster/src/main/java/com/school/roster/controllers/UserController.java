package com.school.roster.controllers;

import java.util.List;



import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.school.roster.model.User;
import com.school.roster.security.AuthenticationRequest;
import com.school.roster.security.AuthenticationResponse;
import com.school.roster.security.JwtUtil;
import com.school.roster.services.UserService;


import jakarta.validation.Valid;
import lombok.experimental.var;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/auth")
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtUtil jwtTokenUtil;
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public  ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
		try {
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
				);
		} catch (BadCredentialsException e) {
			throw new Exception("Incorrect username or password" , e);
		}
		final UserDetails userDetails = userService
				.loadUserByUsername(authenticationRequest.getUsername());
		final String jwt = jwtTokenUtil.generateToken(userDetails);
		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}

		
	@PostMapping("/createUser")
	@ResponseStatus(HttpStatus.CREATED)
	public User createUser(@Valid @RequestBody User signUpRequest) throws BadRequestException {
		if(userService.existsByUsername(signUpRequest.getUsername())) {
			throw new BadRequestException("Username is already taken!");
		}
		
		if(userService.existsByEmail(signUpRequest.getEmail())) {
			throw new BadRequestException("Email is already taken!");
		}
			
		return userService.createUser(signUpRequest);
	}
	
	@GetMapping("/user")
	@ResponseStatus(HttpStatus.OK)
	public User getUser(@RequestBody User user) {
		return userService.getUserByUsername(user.getUsername());
		
	}
	
	@GetMapping("/user/all")
	@ResponseStatus(HttpStatus.OK)
	public List<User> getAllUsers() {
		return userService.getAllUsers();
		
	}
	
	@GetMapping("/user/{id}")
	@ResponseStatus(HttpStatus.OK)
	public User getUser(@PathVariable Long id) {
		return userService.getUserById(id);
		
	}
	
	@GetMapping("/usersRole")
	@ResponseStatus(HttpStatus.OK)
	public List<User> getAllUserByRole(@RequestParam("role") String role) {
		return userService.getAllUserByRole(role);
	}

}
