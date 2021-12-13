package com.example.course.entities;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.example.course.entities.pk.OrderItemPK;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@EqualsAndHashCode(exclude = {"quantity", "price"})
@Entity
@Table(name= "tb_order_item")
public class OrderItem implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private OrderItemPK id = new OrderItemPK();
	
	@Getter@Setter
	@NotNull
	private Integer quantity;
	
	@Getter@Setter
	@NotNull
	private Double price;

	public OrderItem(Order order, Product product, Integer quantity, Double price) {
		super();
		id.setOrder(order);
		id.setProduct(product);
		this.quantity = quantity;
		this.price = price;
	}

	public Double getSubTotal() {
		return price*quantity;
	}
	
	@JsonIgnore
	public Order getOrder() {
		return id.getOrder();
	}
	
	public void setOrder(Order order) {
		id.setOrder(order);
	}
	
	public Product getProduct() {
		return id.getProduct();
	}
	
	public void setProduct(Product product) {
		id.setProduct(product);
	}
}
