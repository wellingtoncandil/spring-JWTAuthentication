package com.example.course.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.course.entities.Order;
import com.example.course.repositories.IOrderRepository;

@Service
public class OrderService {

	@Autowired
	IOrderRepository repository;

	public List<Order> findAll() {
		return repository.findAll();
	}
	
	public Optional<Order> findById(Long id) {
		return repository.findById(id);
	}
}
