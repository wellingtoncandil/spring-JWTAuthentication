package com.example.course.resources;

import java.net.URI;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

import javax.security.auth.login.LoginException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.course.entities.User;
import com.example.course.service.UserService;
import com.example.course.util.Util;

@RestController
@RequestMapping(value= "/users")
public class UserResource {
	
	@Autowired
	UserService service;
	
	@GetMapping
	public ResponseEntity<List<User>> findAll(){
		List<User> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Optional<User>> findById(@PathVariable Long id){
		Optional<User> obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping
	public ResponseEntity<User> insert(@RequestBody User obj) throws Exception{//annotation que informa a chegada do objeto em modo JSON para que seja desserializado para um obj java
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();//cria uma URL para o obj inserido
		return ResponseEntity.created(uri).body(obj); //mostra o obj criado e o local (url) do mesmo
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User obj){
		obj = service.update(id, obj);
		return ResponseEntity.ok(obj);
	}
	
	@PostMapping(value ="/login")
	public ResponseEntity<User> login(@RequestBody User obj) throws NoSuchAlgorithmException, LoginException{
		User user = service.loginUser(obj.getEmail(), Util.md5(obj.getPassword()));
		return ResponseEntity.ok().body(user);
	}
	
	/*@GetMapping(value = "/email/{email}")
	public ResponseEntity<User> findByEmail(@PathVariable String email){
		User em = service.findByEmail(email);
		User u = new User(em.getEmail(), em.getPassword());
		return ResponseEntity.ok().body(u);
	}*/
}
