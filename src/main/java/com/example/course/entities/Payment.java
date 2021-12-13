package com.example.course.entities;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="tb_payment")
public class Payment implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Getter@Setter
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	
	@Getter@Setter
	private Instant moment;
	
	@Setter
	@OneToOne
	@MapsId //mapeamento necessário no relacionamento um para um
	private Order order;

	@JsonIgnore
	public Order getOrder() {
		return order;
	}

}
