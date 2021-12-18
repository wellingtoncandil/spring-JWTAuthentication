package com.example.course.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.course.entities.Role;
import com.example.course.entities.enums.ERole;
import com.example.course.repositories.IRoleRepository;

@Service
public class RoleService {

	@Autowired
	IRoleRepository roleRepository;
	
	public Optional<Role> findByName(ERole name){
		return roleRepository.findByName(name);
	}
}
