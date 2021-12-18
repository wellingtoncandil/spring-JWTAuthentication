package com.example.course.service;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.security.auth.login.LoginException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.course.entities.User;
import com.example.course.repositories.IUserRepository;
import com.example.course.service.exceptions.CriptoExistException;
import com.example.course.service.exceptions.DatabaseException;
import com.example.course.service.exceptions.EmailExistsException;
import com.example.course.service.exceptions.ResourceNotFoundException;
import com.example.course.util.Util;

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

	public User insert(User user) throws Exception {
		try {
			Optional<User> obj = repository.findByEmail(user.getEmail());
			if(obj.isEmpty()){
				String userPass = user.getPassword();
				userPass = Util.md5(user.getPassword());
				user.setPassword(userPass);
			}else {
				throw new EmailExistsException("This email is already had registered");
			}
		}catch (NoSuchAlgorithmException e) {
			throw new CriptoExistException("Password encryption error");
		}
		return repository.save(user);
	}

	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	public User update(Long id, User obj) {
		try {
			User user = repository.getById(id); // método getOne prepara o obj para ser inserido no bd sem que haja a
												// necessidade de busca-lo previamente
			updateData(user, obj);
			return repository.save(user);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	private void updateData(User user, User obj) {
		user.setName(obj.getName());
		user.setEmail(obj.getEmail());
		user.setPhone(obj.getPhone());
	}

	public User loginUser(String email, String password) throws LoginException {
		try {
			User userLogin = repository.searchLogin(email, password);
			if (userLogin == null) {
				throw new ResourceNotFoundException(email);
			} else {
				return userLogin;
			}
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(email);
		}
	}

	public Optional<User> findByEmail(String email) {
		return repository.findByEmail(email);
	}

	public Boolean existsByEmail(String email) {
		return repository.existsByEmail(email);
	}

	Boolean existsByName(String name) {
		return repository.existsByName(name);
	}

}
