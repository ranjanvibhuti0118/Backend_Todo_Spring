package com.project.rest.webservices.restfulwebservices.todo;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


@RestController
@CrossOrigin(origins="http://localhost:4200")
public class TodoJPAResources {

	@Autowired
	private TodoService todoHardCodeService;
	
	@Autowired
	private TodoJPARepository jpaRepository;
	
	@GetMapping(path="/jpa/users/{username}/todos")
	public List<Todo> getAllTodos(@PathVariable String username){

		return jpaRepository.findByUsername(username);
//	return todoHardCodeService.findAll();
		
	}
	@GetMapping(path="/jpa/users/{username}/todos/{id}")
	public Todo getTodo(@PathVariable String username,@PathVariable long id){

//	return todoHardCodeService.findById(id);
	return jpaRepository.findById(id).get();
	}
	
	@PutMapping(path="/jpa/users/{username}/todos/{id}")
	public ResponseEntity<Todo> updateToDo(@PathVariable String username,@PathVariable long id,@RequestBody Todo todo) {
		todo.setUsername(username);

		Todo updatedTodo= jpaRepository.save(todo);
		
		return new ResponseEntity<Todo>(todo, HttpStatus.OK);
	}
	
	@PostMapping(path="/jpa/users/{username}/todos")
	public ResponseEntity<Todo> createToDo(@PathVariable String username,@RequestBody Todo todo) {
		
		
		todo.setUsername(username);
		Todo newTodo= jpaRepository.save(todo);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newTodo.getId()).toUri();
		
		return  ResponseEntity.created(uri).build();
	}
	
	
	
	@DeleteMapping(path="/jpa/users/{username}/todos/{id}")
	public ResponseEntity<Void> deleteTodos(@PathVariable String username,@PathVariable long id){

		
		
		 jpaRepository.deleteById(id);
	
		return ResponseEntity.noContent().build();
	
	}
	
}
