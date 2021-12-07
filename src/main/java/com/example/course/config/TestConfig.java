package com.example.course.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.example.course.entities.User;
import com.example.course.repositories.IUserRepository;

@Configuration // informa que essa é uma classe de configuração
@Profile("test") //informa ao spring que essa configuração só será executada enquanto estiver no perfil de teste (application.properties)
public class TestConfig implements CommandLineRunner{ //classe de configuração para instanciar o bd

	@Autowired // faz a injeção de dependencia de IUserRepository na classe de testes
	private IUserRepository userRepository;

	@Override
	public void run(String... args) throws Exception { //tudo que estiver dentro do método run será executado ao iniciar a aplicação

		User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
		User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456");
		
		userRepository.saveAll(Arrays.asList(u1, u2));//salva os usuarios no bd passando-os como paramentro para uma lista
	}
}
