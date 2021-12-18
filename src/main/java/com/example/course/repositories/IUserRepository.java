package com.example.course.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.course.entities.User;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {
		
	Optional<User> findByEmail(String email);

	@Query("select u from User u where u.email= :email and u.password = :password")
	User searchLogin(String email, String password);

	Optional<User> findByname(String username);
	
	Boolean existsByEmail(String email);
	
	Boolean existsByName(String name);
}
