package com.example.course.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.course.entities.User;

public interface IUserRepository extends JpaRepository<User, Long> {
	
	//User findByEmail(String email);
	
	public List<User> findByEmail(String senha);

	@Query("select u from User u where u.email= :email and u.password = :password")
	User searchLogin(String email, String password);
}
