package com.school.roster.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.school.roster.model.StudentProfile;
import com.school.roster.model.User;
import com.school.roster.services.StudentProfileService;
import com.school.roster.services.UserService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/student/profile")
public class StudentProfileController {

	@Autowired
	StudentProfileService studentProfileService;
	@Autowired
	UserService userService;
	
	@PostMapping("/create")
	@ResponseStatus(HttpStatus.CREATED)
	public StudentProfile creatProfile(@RequestBody StudentProfile profile) {
		User user = userService.getUserByUsername(profile.getUser().getUsername());
		return studentProfileService.createStudentProfile(profile, user);
	}

	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public StudentProfile getStudentProfileById(@PathVariable Long id) {
		return studentProfileService.getProfileById(id);
	}
	
	@GetMapping("/user/{id}")
	@ResponseStatus(HttpStatus.OK)
	public StudentProfile getStudentProfileByUserId(@PathVariable long id) {
		return studentProfileService.getProfileByUserId(id);
	}
	
	@GetMapping("/all")
	@ResponseStatus(HttpStatus.OK)
	public List<StudentProfile> getAllStudentProfile() {
		return studentProfileService.getAllStudentProfiles();
	}
}
