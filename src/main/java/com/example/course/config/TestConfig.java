package com.example.course.config;

import java.time.Instant;
import java.util.Arrays;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.example.course.entities.Category;
import com.example.course.entities.Order;
import com.example.course.entities.OrderItem;
import com.example.course.entities.Payment;
import com.example.course.entities.Product;
import com.example.course.entities.Role;
import com.example.course.entities.User;
import com.example.course.entities.enums.ERole;
import com.example.course.entities.enums.OrderStatus;
import com.example.course.repositories.ICategoryRepository;
import com.example.course.repositories.IOrderItemRepository;
import com.example.course.repositories.IOrderRepository;
import com.example.course.repositories.IProductRepository;
import com.example.course.repositories.IRoleRepository;
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
	@Autowired
	private IProductRepository productRepository;
	@Autowired
	private IOrderItemRepository orderItemRepository;
	@Autowired
	private IRoleRepository roleRepository;

	@Override
	public void run(String... args) throws Exception { //tudo que estiver dentro do método run será executado ao iniciar a aplicação

		User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
		User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456");
		
		Order o1 = new Order(null, Instant.parse("2019-06-20T19:53:07Z"), OrderStatus.PAID, u1);
		Order o2 = new Order(null, Instant.parse("2019-07-21T03:42:10Z"), OrderStatus.WAITING_PAYMENT, u2);
		Order o3 = new Order(null, Instant.parse("2019-07-22T15:21:22Z"), OrderStatus.PAID, u1);
		//Order o4 = new Order(null, Instant.now(), OrderStatus.PAID, u1);
		
		Role role1 = new Role(ERole.ROLE_ADMIN);
		Role role2 = new Role(ERole.ROLE_MODERATOR);
		Role role3 = new Role(ERole.ROLE_USER);
		
		Category c1 = new Category(null, "Electronics");
		Category c2 = new Category(null, "Books");
		Category c3 = new Category(null, "Computers"); 
		
		/*Product p1 = new Product(null, "The Lord of the Rings", "Lorem ipsum dolor sit amet, consectetur.", 90.5, "");
		Product p2 = new Product(null, "Smart TV", "Nulla eu imperdiet purus. Maecenas ante.", 2190.0, "");
		Product p3 = new Product(null, "Macbook Pro", "Nam eleifend maximus tortor, at mollis.", 1250.0, "");
		Product p4 = new Product(null, "PC Gamer", "Donec aliquet odio ac rhoncus cursus.", 1200.0, "");
		Product p5 = new Product(null, "Rails for Dummies", "Cras fringilla convallis sem vel faucibus.", 100.99, "");*/
		
		Product p1 = new Product(null, "The Lord of the Rings", "Lorem ipsum dolor sit amet, consectetur.", 90.5, "", new HashSet<>(Arrays.asList(c2)));
		Product p2 = new Product(null, "Smart TV", "Nulla eu imperdiet purus. Maecenas ante.", 2190.0, "", new HashSet<>(Arrays.asList(c1, c3)));
		Product p3 = new Product(null, "Macbook Pro", "Nam eleifend maximus tortor, at mollis.", 1250.0, "", new HashSet<>(Arrays.asList(c3)));
		Product p4 = new Product(null, "PC Gamer", "Donec aliquet odio ac rhoncus cursus.", 1200.0, "", new HashSet<>(Arrays.asList(c3)));
		Product p5 = new Product(null, "Rails for Dummies", "Cras fringilla convallis sem vel faucibus.", 100.99, "", new HashSet<>(Arrays.asList(c2)));
		
		userRepository.saveAll(Arrays.asList(u1, u2));//salva os usuarios no bd passando-os como paramentro para uma lista
		orderRepository.saveAll(Arrays.asList(o1, o2, o3));
		categoryRepository.saveAll(Arrays.asList(c1, c2, c3));
		productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));
		roleRepository.saveAll(Arrays.asList(role1, role2, role3));
		
		/*p1.getCategories().add(c2);
		p2.getCategories().add(c1);
		p2.getCategories().add(c3);
		p3.getCategories().add(c3);
		p4.getCategories().add(c3);
		p5.getCategories().add(c2);*/
		
		//productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));
		
		OrderItem oi1 = new OrderItem(o1, p1, 2, p1.getPrice());
		OrderItem oi2 = new OrderItem(o1, p3, 1, p3.getPrice());
		OrderItem oi3 = new OrderItem(o2, p3, 2, p3.getPrice());
		OrderItem oi4 = new OrderItem(o3, p5, 2, p5.getPrice());
		
	    orderItemRepository.saveAll(Arrays.asList(oi1, oi2, oi3, oi4));
	
		Payment pay = new Payment(null, Instant.parse("2019-07-22T16:21:22Z"), o3);
		o3.setPayment(pay);
		
		orderRepository.save(o3);
	}
}
