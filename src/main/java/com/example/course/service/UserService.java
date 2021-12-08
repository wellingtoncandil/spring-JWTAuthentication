package com.example.course.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.course.entities.User;
import com.example.course.repositories.IUserRepository;

@Service
public class UserService {

	@Autowired
	IUserRepository repository;

	public List<User> findAll() {
		return repository.findAll();
	}
	
	public Optional<User> findById(Long id) {
		return repository.findById(id);
	}
	
	public User insert(User user) {
		return repository.save(user);
	}
}
