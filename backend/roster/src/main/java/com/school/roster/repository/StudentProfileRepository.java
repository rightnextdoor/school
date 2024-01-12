package com.school.roster.repository;

import java.util.Date;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.school.roster.model.StudentProfile;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface StudentProfileRepository extends JpaRepository<StudentProfile, Long>{
	Optional<StudentProfile> findByUserId(long userId);

	@Modifying
	@Transactional
	@Query("update address u set u.streetAddress = :street_address where u.id = :id")
	void updateStreetAddress(@Param(value = "id") long id, @Param(value = "street_address") String streetAddress);
	@Modifying
	@Transactional
	@Query("update address u set u.city = :city where u.id = :id")
	void updateCity(@Param(value = "id") long id, @Param(value = "city") String city);
	@Modifying
	@Transactional
	@Query("update address u set u.state = :state where u.id = :id")
	void updateState(@Param(value = "id") long id, @Param(value = "state") String state);
	@Modifying
	@Transactional
	@Query("update address u set u.zipCode = :zip_code where u.id = :id")
	void updateZipCode(@Param(value = "id") long id, @Param(value = "zip_code") String zipCode);

	@Modifying
	@Transactional
	@Query("update phone_number u set u.phoneNumber = :phone_number where u.id = :id")
	void updatePhoneNumber(@Param(value = "id") long id, @Param(value = "phone_number") String phoneNumber);
	@Modifying
	@Transactional
	@Query("update phone_number u set u.phoneType = :phone_type where u.id = :id")
	void updatePhoneType(@Param(value = "id") long id, @Param(value = "phone_type") String phoneType);
	@Modifying
	@Transactional
	@Query("update school_background u set u.schoolId = :school_id where u.id = :id")
	void updateSchoolId(@Param(value = "id") long id, @Param(value = "school_id") String schoolId);
	@Modifying
	@Transactional
	@Query("update school_background u set u.gradeLevel = :grade_level where u.id = :id")
	void updateGradeLevel(@Param(value = "id") long id, @Param(value = "grade_level") String gradeLevel);
	@Modifying
	@Transactional
	@Query("update school_background u set u.startOfDate= :start_of_date where u.id = :id")
	void updateStartOfDate(@Param(value = "id") long id, @Param(value = "start_of_date") Date startOfDate);

	@Modifying
	@Transactional
	@Query("delete from address u where u.id = :id")
	void deleteAddress(@Param(value = "id") long id);
	@Modifying
	@Transactional
	@Query("delete from phone_number u where u.id = :id")
	void deletePhoneNumber(@Param(value = "id") long id);
	@Modifying
	@Transactional
	@Query("delete from school_background u where u.id = :id")
	void deleteSchoolBackground(@Param(value = "id") long id);

}
