package com.school.roster.services;

import java.util.List;

import com.school.roster.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	public StudentProfile addAddress(AddressStudent address, User user){
		StudentProfile profile = getProfileByUserId(user.getId());
		profile.addAddress(address);
		return studentProfileRepository.save(profile);
	}

	public StudentProfile addPhone(PhoneNumberStudent phone, User user){
		StudentProfile profile = getProfileByUserId(user.getId());
		profile.addPhoneNumber(phone);
		return studentProfileRepository.save(profile);
	}
	public StudentProfile addSchool(SchoolBackgroundStudent school, User user){
		StudentProfile profile = getProfileByUserId(user.getId());
		profile.addSchoolBackground(school);
		return studentProfileRepository.save(profile);
	}

	public  StudentProfile updateProfile(StudentProfile updateProfile, User user){
		StudentProfile profile = getProfileByUserId(user.getId());

		profile.setName(updateProfile.getName());
		profile.setDateOfBirth(updateProfile.getDateOfBirth());
		profile.setMotherName(updateProfile.getMotherName());
		profile.setFatherName(updateProfile.getFatherName());
		profile.setGender(updateProfile.getGender());
		profile.setEthnicity(updateProfile.getEthnicity());
		profile.setReligious(updateProfile.getReligious());
		updateLanguage(updateProfile, profile);
		updateNationality(updateProfile, profile);
		updateAddress(updateProfile);
		updatePhoneNumber(updateProfile);
		updateSchoolBackground(updateProfile);

		return studentProfileRepository.save(profile);
	}

	private void updateNationality(StudentProfile updateProfile, StudentProfile profile) {
		profile.getNationality().clear();
		profile.setNationality(updateProfile.getNationality());
	}

	private void updateLanguage(StudentProfile updateProfile, StudentProfile profile) {
		profile.getLanguage().clear();
		profile.setLanguage(updateProfile.getLanguage());
	}


	private void updateSchoolBackground(StudentProfile updateProfile) {
		for(SchoolBackgroundStudent school: updateProfile.getSchoolBackgrounds()){
			studentProfileRepository.updateSchoolId(school.getId(), school.getSchoolId());
			studentProfileRepository.updateGradeLevel(school.getId(), school.getGradeLevel());
			studentProfileRepository.updateStartOfDate(school.getId(), school.getStartOfDate());
		}
	}

	private void updatePhoneNumber(StudentProfile updateProfile) {
		for (PhoneNumberStudent phone: updateProfile.getPhoneNumbers()){
			studentProfileRepository.updatePhoneNumber(phone.getId(), phone.getPhoneNumber());
			studentProfileRepository.updatePhoneType(phone.getId(), phone.getPhoneType());
		}
	}

	private void updateAddress(StudentProfile updateProfile) {
		for (AddressStudent address: updateProfile.getAddresses()){
			studentProfileRepository.updateCity(address.getId(), address.getCity());
			studentProfileRepository.updateState(address.getId(),address.getState());
			studentProfileRepository.updateStreetAddress(address.getId(), address.getStreetAddress());
			studentProfileRepository.updateZipCode(address.getId(), address.getZipCode());
		}
	}

}
