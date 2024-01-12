package com.school.roster.model;

import jakarta.persistence.*;

@Entity(name = "address")
public class AddressStudent {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String streetAddress;
	private String city;
	private String state;
	private String zipCode;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "profile_id", referencedColumnName = "id")
	private StudentProfile profile;

	public AddressStudent() {
		super();
	}

	public AddressStudent(String streetAddress, String city, String state, String zipCode, StudentProfile profile) {
		super();
		this.streetAddress = streetAddress;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
		this.profile = profile;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public void setProfile(StudentProfile profile) {
		this.profile = profile;
	}
}
