package com.example.course.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.example.course.entities.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode(exclude = {"moment", "orderStatus", "client", "items", "payment"})
@Entity
@Table(name = "tb_order") //faz com que o nome da tabela no bd seja "tb_order"
public class Order implements Serializable{

	private static final long serialVersionUID = 1L;

	@Getter@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Getter@Setter
	//garante que o json seja mostrado no formato de string ISO8601
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
	private Instant moment;
	
	private Integer orderStatus;
	
	@Getter@Setter
	@ManyToOne // informa o relacionamento "muitos para um" com a tabela User
	@JoinColumn(name = "client_id") //insere a coluna "client_id"
	private User client;

	@Getter
	@OneToMany(mappedBy= "id.order") // o OrderItem contem o "id" que por sua vez contem o order
	private Set<OrderItem> items = new HashSet<>();
	
	@Getter@Setter
	@OneToOne(mappedBy="order", cascade = CascadeType.ALL)//mapeamento das duas entidades para que tenham o mesmo ID
	private Payment payment;
	
	public Order() {
	}
	
	public Order(Long id, Instant moment, OrderStatus orderStatus, User client) {
		super();
		this.id = id;
		this.moment = moment;
		setOrderStatus(orderStatus);
		this.client = client;
	}

	public OrderStatus getOrderStatus() {
		return OrderStatus.valueOf(orderStatus);
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		if(orderStatus != null) {
			this.orderStatus = orderStatus.getCode();
		}
	}

	public Double getTotal() {
		double sum = 0.0;
		for(OrderItem x : items) {
			sum += x.getSubTotal();
		}
		return sum;
	}
}