package com.school.roster.services;

import java.util.List;

import com.school.roster.model.PhoneNumberStudent;
import com.school.roster.model.SchoolBackgroundStudent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.roster.model.StudentProfile;
import com.school.roster.model.User;
import com.school.roster.repository.StudentProfileRepository;

@Service
public class StudentProfileService {

	@Autowired
	StudentProfileRepository studentProfileRepository;
	
	public StudentProfile createStudentProfile(StudentProfile studentProfile, User user) {
		StudentProfile createProfile = new StudentProfile(studentProfile.getName(), studentProfile.getDateOfBirth(),
				studentProfile.getMotherName(), studentProfile.getFatherName(), studentProfile.getGender(),
				studentProfile.getEthnicity(), studentProfile.getReligious(), studentProfile.getLanguage(),
				studentProfile.getNationality(), user);
		createProfile.addAddress(studentProfile.getAddresses(), createProfile);
		createProfile.addPhoneNumber(studentProfile.getPhoneNumbers(), createProfile);
		createProfile.addSchoolBackground(studentProfile.getSchoolBackgrounds(), createProfile);

		return studentProfileRepository.save(createProfile);
	}

	public StudentProfile getProfileByUserId(long userID) {
		return studentProfileRepository.findByUserId(userID).get();
	}
	
	public StudentProfile getProfileById(long id ) {
		return studentProfileRepository.getById(id);
	}
	
	public List<StudentProfile> getAllStudentProfiles() {
		return studentProfileRepository.findAll();
	}
}
