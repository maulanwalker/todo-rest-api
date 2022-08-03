package com.example.todorestapi.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.todorestapi.model.TodoModel;
import com.example.todorestapi.repository.TodoRepository;

@RestController
@RequestMapping("/api")
public class TodoController {

	@Autowired
	TodoRepository todoRepository;

	@GetMapping("/todos")
	public ResponseEntity<List<TodoModel>> getAllTodo(@RequestParam(required = false) String text) {
		try {
			List<TodoModel> todos = new ArrayList<TodoModel>();

			if (text == null) {
				todoRepository.findAll().forEach(todos::add);
			} else {
				todoRepository.findByTodoTextContaining(text).forEach(todos::add);			
			}
			
			if (todos.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(todos, HttpStatus.OK);
		} catch (Exception err) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/todos/{id}")
	public ResponseEntity<TodoModel> getTodoById(@PathVariable("id") long id) {
		Optional<TodoModel> todoData = todoRepository.findById(id);

		if (todoData.isPresent()) {
			return new ResponseEntity<>(todoData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/todos")
	public ResponseEntity<TodoModel> createTodo(@RequestBody TodoModel todoModel){
		try {
			TodoModel todo = todoRepository.save(new TodoModel(todoModel.getTodoText(), todoModel.isTodoIsCompleted()));
			return new ResponseEntity<>(todo, HttpStatus.CREATED);
		}catch(Exception err){
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/todos/{id}")
	public ResponseEntity<TodoModel> updateTodoModel(@PathVariable("id") long id, @RequestBody TodoModel todoModel) {
		Optional<TodoModel> todoData = todoRepository.findById(id);

		if (todoData.isPresent()) {
			TodoModel todo = todoData.get();
			todo.setTodoText(todoModel.getTodoText());
			todo.setTodoIsCompleted(todoModel.isTodoIsCompleted());
			return new ResponseEntity<>(todoRepository.save(todo), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/todos/{id}")
	public ResponseEntity<HttpStatus> deleteTodoModel(@PathVariable("id") long id) {
		try {
			todoRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception err) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}	
}
