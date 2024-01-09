package com.school.roster.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.school.roster.model.MyUserDetails;
import com.school.roster.model.User;
import com.school.roster.repository.UserRepository;

@Service
public class UserService implements UserDetailsService{
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<User> user = userRepository.findByUsernameOrEmail(username,username);
		
		user.orElseThrow(() -> new UsernameNotFoundException("Not found: " + username));
		
		return user.map(MyUserDetails::new).get();
	}
	
	public User createUser(User _user) {
		User user = new User(_user.getUsername(), passwordEncoder.encode(_user.getPassword()), _user.getEmail(), _user.isActive(), _user.getRole());
		userRepository.save(user);
		return user;
	}
	
	public User getUserById(long id) {
		return userRepository.getById(id);
	}
	
	public User getUserByEmail(String email) {
		return userRepository.findByEmail(email).get();
	}
	
	public User getUserByUsername(String username) {
		return userRepository.findByUsername(username).get();
	}
	
	public Boolean existsByUsername(String username) {
		return userRepository.findByUsername(username).isPresent();
	}
	
	public Boolean existsByEmail(String email) {
		return userRepository.findByEmail(email).isPresent();
	}
	
	public List<User> getAllUserByRole(String role) {
		List<User> user = new ArrayList<>();
		userRepository.findAllByRole(role)
		.forEach(user::add);
		return user;
	}
	
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}
}
