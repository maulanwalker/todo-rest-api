package com.example.todorestapi.repository;

//import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.todorestapi.model.TodoModel;

public interface TodoRepository extends JpaRepository<TodoModel, Long> {

	List<TodoModel> findByTodoTextContaining(String todo_text);
//	  List<TodoModel> findByTodoByCompleted(boolean todo_is_completed);

//	  List<TodoModel> findByTitleContaining(String todo_text);
}
