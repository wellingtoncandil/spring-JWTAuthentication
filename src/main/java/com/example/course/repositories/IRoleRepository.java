package com.example.course.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.course.entities.Role;
import com.example.course.entities.enums.ERole;

@Repository
public interface IRoleRepository extends JpaRepository<Role, Long>{

	Optional<Role> findByName(ERole name);
}
