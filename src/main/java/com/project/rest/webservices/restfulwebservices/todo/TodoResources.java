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
public class TodoResources {

	@Autowired
	private TodoService todoHardCodeService;
	
	@GetMapping(path="/users/{username}/todos")
	public List<Todo> getAllTodos(@PathVariable String username){

	return todoHardCodeService.findAll();
		
	}
	@GetMapping(path="/users/{username}/todos/{id}")
	public Todo getTodo(@PathVariable String username,@PathVariable long id){

	return todoHardCodeService.findById(id);
		
	}
	
	@PutMapping(path="/users/{username}/todos/{id}")
	public ResponseEntity<Todo> updateToDo(@PathVariable String username,@PathVariable long id,@RequestBody Todo todo) {
		
		Todo updatedTodo= todoHardCodeService.save(todo);
		
		return new ResponseEntity<Todo>(todo, HttpStatus.OK);
	}
	
	@PostMapping(path="/users/{username}/todos")
	public ResponseEntity<Todo> createToDo(@PathVariable String username,@RequestBody Todo todo) {
		
		Todo newTodo= todoHardCodeService.save(todo);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newTodo.getId()).toUri();
		
		return  ResponseEntity.created(uri).build();
	}
	
	
	
	@GetMapping("/getMsg")
	public void getMsg() {
		System.out.println("hello");
	}
	
	@DeleteMapping(path="/users/{username}/todos/{id}")
	public ResponseEntity<Void> deleteTodos(@PathVariable String username,@PathVariable long id){

		
		
		Todo todo = todoHardCodeService.deleteById(id);
		if(todo!=null) {
			return ResponseEntity.noContent().build();
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	
}
