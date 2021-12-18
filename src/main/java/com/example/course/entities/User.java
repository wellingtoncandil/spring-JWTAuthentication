package com.example.course.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
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

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@Entity
@Table(name = "tb_user")
public class User implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)//definição de estratégia de auto incremento
	private Long id;
	@NotNull
	private String name;
	@NotNull
	private String email;

	private String phone;
	@NotNull
	private String password;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "tb_user_roles",
	joinColumns = @JoinColumn(name = "user_id"),
	inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();
	
	@Setter(AccessLevel.NONE)
	@JsonIgnore // jackson annotation que é usada para ignorar a lista de propriedades no json, evitando assim um loop infinito
	@OneToMany(mappedBy = "client")// informa que "orders" é associado como um para muitos com a tabela Order no bd,e mapeado pela chave client
	private List<Order> orders = new ArrayList<>();
	
	public User(String username, String email, String password) {
		this.name = username;
		this.email = email;
		this.password = password;
	}
	
	public User(String email, String password) {
		this.email = email;
		this.password = password;
	}

	public User(Long id, String name, String email, String phone, String password) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.password = password;	
	}
}
