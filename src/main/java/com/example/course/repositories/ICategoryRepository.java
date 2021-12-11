package com.example.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.course.entities.Category;

public interface ICategoryRepository extends JpaRepository<Category, Long> {

	public Category findByName(String name);
}
