package com.example.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.course.entities.Order;

public interface IOrderRepository extends JpaRepository<Order, Long> {

}
