package com.example.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.course.entities.OrderItem;
import com.example.course.entities.pk.OrderItemPK;

public interface IOrderItemRepository extends JpaRepository<OrderItem, OrderItemPK> {

}