package com.example.course.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.example.course.entities.Category;
import com.example.course.entities.Order;
import com.example.course.entities.User;
import com.example.course.entities.enums.OrderStatus;
import com.example.course.repositories.ICategoryRepository;
import com.example.course.repositories.IOrderRepository;
import com.example.course.repositories.IUserRepository;

@Configuration // informa que essa é uma classe de configuração
@Profile("test") //informa ao spring que essa configuração só será executada enquanto estiver no perfil de teste (application.properties)
public class TestConfig implements CommandLineRunner{ //classe de configuração para instanciar o bd

	@Autowired // faz a injeção de dependencia de IUserRepository na classe de testes
	private IUserRepository userRepository;
	@Autowired
	private IOrderRepository orderRepository;
	@Autowired
	private ICategoryRepository categoryRepository;

	@Override
	public void run(String... args) throws Exception { //tudo que estiver dentro do método run será executado ao iniciar a aplicação

		User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
		User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456");
		
		Order o1 = new Order(null, Instant.parse("2019-06-20T19:53:07Z"), OrderStatus.PAID, u1);
		Order o2 = new Order(null, Instant.parse("2019-07-21T03:42:10Z"), OrderStatus.WAITING_PAYMENT, u2);
		Order o3 = new Order(null, Instant.parse("2019-07-22T15:21:22Z"), OrderStatus.WAITING_PAYMENT, u1);
		
		Category c1 = new Category(null, "Electronics");
		Category c2 = new Category(null, "Books");
		Category c3 = new Category(null, "Computers"); 
		
		userRepository.saveAll(Arrays.asList(u1, u2));//salva os usuarios no bd passando-os como paramentro para uma lista
		orderRepository.saveAll(Arrays.asList(o1, o2, o3));
		categoryRepository.saveAll(Arrays.asList(c1, c2, c3));
	}
}
