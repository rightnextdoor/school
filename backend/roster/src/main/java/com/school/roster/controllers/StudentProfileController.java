package com.school.roster.controllers;

import java.util.List;

import com.school.roster.model.AddressStudent;
import com.school.roster.payload.AddressRequest;
import com.school.roster.payload.PhoneRequest;
import com.school.roster.payload.SchoolRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

	@PostMapping("/add/address")
	public StudentProfile addAddress(@RequestBody AddressRequest address){
		User user = userService.getUserByUsername(address.getUser().getUsername());
		return studentProfileService.addAddress(address.getAddress(), user);
	}
	@PostMapping("/add/phone")
	public StudentProfile addPhone(@RequestBody PhoneRequest phone){
		User user = userService.getUserByUsername(phone.getUser().getUsername());
		return studentProfileService.addPhone(phone.getPhoneNumber(), user);
	}
	@PostMapping("/add/school")
	public StudentProfile addSchool(@RequestBody SchoolRequest school){
		User user = userService.getUserByUsername(school.getUser().getUsername());
		return studentProfileService.addSchool(school.getSchoolBackground(), user);
	}

	@PatchMapping("/update")
	public StudentProfile updateProfile(@RequestBody StudentProfile profile){
		User user = userService.getUserByUsername(profile.getUser().getUsername());
		return studentProfileService.updateProfile(profile,user);
	}

}
