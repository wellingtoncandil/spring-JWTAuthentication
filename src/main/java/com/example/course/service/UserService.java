package com.example.course.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.course.entities.User;
import com.example.course.repositories.IUserRepository;
import com.example.course.service.exceptions.ResourceNotFoundException;

@Service
public class UserService {

	@Autowired
	IUserRepository repository;

	public List<User> findAll() {
		return repository.findAll();
	}

	public Optional<User> findById(Long id) {
		Optional<User> obj = repository.findById(id);
		return Optional.of(obj.orElseThrow(() -> new ResourceNotFoundException(id)));
	}

	public User insert(User user) {
		return repository.save(user);
	}

	public void delete(Long id) {
		repository.deleteById(id);
	}
	
	public User update(Long id, User obj) {
		User user = repository.getById(id); //método getOne prepara o obj para ser inserido no bd sem que haja a necessidade de busca-lo previamente
		updateData(user, obj);
		return repository.save(user);
	}

	private void updateData(User user, User obj) {
		user.setName(obj.getName());
		user.setEmail(obj.getEmail());
		user.setPhone(obj.getPhone());
	}
}
