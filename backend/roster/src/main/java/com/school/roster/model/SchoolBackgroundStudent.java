package com.school.roster.model;

import java.util.Date;

import jakarta.persistence.*;

@Entity(name = "school_background")
public class SchoolBackgroundStudent {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "profile_id", referencedColumnName = "id")
	private StudentProfile profile;
	
	private String schoolId;
	private String gradeLevel;
	private Date startOfDate;
	
	public SchoolBackgroundStudent() {
		super();
	}
	public SchoolBackgroundStudent(StudentProfile profile, String schoolId, String gradeLevel, Date startOfDate) {
		super();
		this.profile = profile;
		this.schoolId = schoolId;
		this.gradeLevel = gradeLevel;
		this.startOfDate = startOfDate;
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
	public String getSchoolId() {
		return schoolId;
	}
	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}
	public String getGradeLevel() {
		return gradeLevel;
	}
	public void setGradeLevel(String gradeLevel) {
		this.gradeLevel = gradeLevel;
	}
	public Date getStartOfDate() {
		return startOfDate;
	}
	public void setStartOfDate(Date startOfDate) {
		this.startOfDate = startOfDate;
	}
}
