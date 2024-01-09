package com.school.roster.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.school.roster.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
	Optional<User> findByUsernameOrEmail(String username, String email);
	Optional<User> findByEmail(String email);
	Optional<User> findByUsername(String username);
	
	@Query("select u from User u where u.role like %:role%")
	List<User> findAllByRole(@Param("role") String role);
}
