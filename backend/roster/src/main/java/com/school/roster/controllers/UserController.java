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



@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/user")
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtUtil jwtTokenUtil;
	
	
	
	@GetMapping("/")
	@ResponseStatus(HttpStatus.OK)
	public User getUser(@RequestBody User user) {
		return userService.getUserByUsername(user.getUsername());
		
	}
	
	@GetMapping("/all")
	@ResponseStatus(HttpStatus.OK)
	public List<User> getAllUsers() {
		return userService.getAllUsers();
		
	}
	
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public User getUser(@PathVariable Long id) {
		return userService.getUserById(id);
		
	}
	
	@GetMapping("/Role")
	@ResponseStatus(HttpStatus.OK)
	public List<User> getAllUserByRole(@RequestParam("role") String role) {
		return userService.getAllUserByRole(role);
	}

}
