package com.example.course.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode(exclude = {"name", "description", "price", "imgUrl", "categories", "items"})
@Entity
@Table(name = "tb_product")
public class Product implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Getter@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Getter@Setter
	@NotNull
	private String name;
	
	@Getter@Setter
	@NotNull
	private String description;
	
	@Getter@Setter
	@NotNull
	private Double price;

	@Getter@Setter
	private String imgUrl;
	
	@ManyToMany
	@JoinTable(name="tb_product_category",
	joinColumns = @JoinColumn(name="product_id"),
	inverseJoinColumns = @JoinColumn(name="category_id"))
	private Set<Category> categories = new HashSet<>();
	
	@OneToMany(mappedBy = "id.product")//collection de OrderItems que retornará todos os OrderItems que contenham o produto
	private Set<OrderItem> items = new HashSet<>();	
	
	public Product() {}

	public Product(Long id, String name, String description, Double price, String imgUrl, Set<Category> categories ) {
		super();
		this.id = id;
		setName(name);
		this.description = description;
		this.price = price;
		this.imgUrl = imgUrl;
		for(Category cat:categories) {
			this.categories.add(cat);
		}
	}
	
	public Set<Category> getCategories() {
		return categories;
	}

	@JsonIgnore
	public Set<Order> getOrders() {
		Set<Order> set = new HashSet<>();
		for (OrderItem x : items) {
			set.add(x.getOrder());
		}
		return set;
	}
}