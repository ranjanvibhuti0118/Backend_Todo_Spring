package com.project.rest.webservices.restfulwebservices.todo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class TodoService {

	private static List<Todo> todos= new ArrayList();
	private static long idCounter=0;

	static {
		todos.add(new Todo(++idCounter,"Vibhuti","Learn SpringBoot",new Date(), false));
		todos.add(new Todo(++idCounter,"Vibhuti","Learn React",new Date(), false));
		todos.add(new Todo(++idCounter,"Vibhuti","Learn Java",new Date(), false));
	}
	public List<Todo> findAll(){
		return todos;
	}
	
	public Todo deleteById(long id){
		Todo toDeleteTodo= findById(id);	
		
		if(toDeleteTodo==null) return null;
		
		todos.remove(toDeleteTodo);
		return toDeleteTodo;
	}
	
	public Todo save(Todo todo) {
		if(todo.getId()==-1 || todo.getId()==0) {
			todo.setId(++idCounter);
			todos.add(todo);
		}
		else {
			deleteById(todo.getId());
			todos.add(todo);
		}
		return todo;
	}
	public Todo findById(long id) {

		for(Todo todo:todos) {
			if(todo.getId()==id){
				return todo;
			}
		}
		return null;
	}
}
