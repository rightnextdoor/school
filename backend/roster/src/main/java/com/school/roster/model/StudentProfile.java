package com.school.roster.model;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


import jakarta.persistence.*;

@Entity
@Table(name = "student_profile", uniqueConstraints = {
		@UniqueConstraint(columnNames = {
				"id",
				"user_id"
		})
})
public class StudentProfile {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;
	//format local date to a year, month and date
	private LocalDate dateOfBirth;
	private int age;
	private String motherName;
	private String fatherName;
	private String gender;
	private String ethnicity;
	private String religious;

	@ElementCollection(targetClass = String.class)
	private Set<String> language = new HashSet<>();;
	@ElementCollection(targetClass = String.class)
	private Set<String> nationality = new HashSet<>();

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "profile")
	private List<PhoneNumberStudent> phoneNumbers = new ArrayList<>();

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "profile")
	private List<AddressStudent> addresses = new ArrayList<>();;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "profile")
	private List<SchoolBackgroundStudent> schoolBackgrounds = new ArrayList<>();;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	public StudentProfile() {
		super();
	}

	public StudentProfile(String name, LocalDate dateOfBirth, String motherName, String fatherName,
			String gender, String ethnicity, String religious, Set<String> language, Set<String> nationality,
			User user) {
		super();
		this.name = name;
		this.dateOfBirth = dateOfBirth;
		this.age = calculateAge(dateOfBirth);
		this.motherName = motherName;
		this.fatherName = fatherName;
		this.gender = gender;
		this.ethnicity = ethnicity;
		this.religious = religious;
		this.language = language;
		this.nationality = nationality;
		this.user = user;
	}

	public void addAddress(List<AddressStudent> addressList,StudentProfile profile) {
		for (AddressStudent address : addressList) {
			AddressStudent createAddress = new AddressStudent(address.getStreetAddress(), address.getCity(), address.getState(), address.getZipCode(), profile);
			profile.getAddresses().add(createAddress);
		}
	}

	public void addAddress(AddressStudent address) {
		AddressStudent createAddress = new AddressStudent(address.getStreetAddress(),
				address.getCity(), address.getState(), address.getZipCode(), this);
		addresses.add(createAddress);
	}

	public void addPhoneNumber(List<PhoneNumberStudent> phoneNumberList, StudentProfile profile) {
		for (PhoneNumberStudent phoneNumber : phoneNumberList) {
			PhoneNumberStudent createNumber = new PhoneNumberStudent(profile, phoneNumber.getPhoneType(), phoneNumber.getPhoneNumber());
			profile.getPhoneNumbers().add(createNumber);
		}
	}

	public void addPhoneNumber(PhoneNumberStudent phone) {
		PhoneNumberStudent createPhone = new PhoneNumberStudent(this,phone.getPhoneType(), phone.getPhoneNumber());
		phoneNumbers.add(createPhone);
	}

	public void addSchoolBackground(List<SchoolBackgroundStudent> schoolList, StudentProfile profile) {
		for (SchoolBackgroundStudent schoolBackground : schoolList) {
			SchoolBackgroundStudent createSchool = new SchoolBackgroundStudent(profile, schoolBackground.getSchoolId(),
					schoolBackground.getGradeLevel(), schoolBackground.getStartOfDate());
			profile.getSchoolBackgrounds().add(createSchool);
		}
	}
	public void addSchoolBackground(SchoolBackgroundStudent school){
		SchoolBackgroundStudent createSchool = new SchoolBackgroundStudent(this, school.getSchoolId(),
				school.getGradeLevel(), school.getStartOfDate());
		schoolBackgrounds.add(createSchool);
	}

	public int calculateAge(LocalDate birthDate) {
		LocalDate currentDate = LocalDate.now();
			    return Period.between(birthDate, currentDate).getYears();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getMotherName() {
		return motherName;
	}

	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEthnicity() {
		return ethnicity;
	}

	public void setEthnicity(String ethnicity) {
		this.ethnicity = ethnicity;
	}

	public String getReligious() {
		return religious;
	}

	public void setReligious(String religious) {
		this.religious = religious;
	}

	public Set<String> getLanguage() {
		return language;
	}

	public void setLanguage(Set<String> language) {
		this.language = language;
	}

	public Set<String> getNationality() {
		return nationality;
	}

	public void setNationality(Set<String> nationality) {
		this.nationality = nationality;
	}

	public List<PhoneNumberStudent> getPhoneNumbers() {
		return phoneNumbers;
	}

	public void setPhoneNumbers(List<PhoneNumberStudent> phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}

	public List<AddressStudent> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<AddressStudent> addresses) {
		this.addresses = addresses;
	}

	public List<SchoolBackgroundStudent> getSchoolBackgrounds() {
		return schoolBackgrounds;
	}

	public void setSchoolBackgrounds(List<SchoolBackgroundStudent> schoolBackgrounds) {
		this.schoolBackgrounds = schoolBackgrounds;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}


}
