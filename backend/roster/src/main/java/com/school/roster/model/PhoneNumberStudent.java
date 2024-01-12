package com.school.roster.model;

import jakarta.persistence.*;

@Entity(name = "phone_number")
public class PhoneNumberStudent {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "profile_id", referencedColumnName = "id")
	private StudentProfile profile;

	private String phoneType;
	private String phoneNumber;

	public PhoneNumberStudent() {
		super();
	}

	public PhoneNumberStudent(StudentProfile profile, String phoneType, String phoneNumber) {
		super();
		this.profile = profile;
		this.phoneType = phoneType;
		this.phoneNumber = phoneNumber;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setProfile(StudentProfile profile) {
		this.profile = profile;
	}

	public String getPhoneType() {
		return phoneType;
	}

	public void setPhoneType(String phoneType) {
		this.phoneType = phoneType;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
}
