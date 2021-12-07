package com.example.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.course.entities.Product;

public interface IProductRepository extends JpaRepository<Product, Long> {

}
