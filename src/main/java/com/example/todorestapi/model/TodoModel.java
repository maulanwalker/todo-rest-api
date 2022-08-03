package com.example.todorestapi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "todos")
public class TodoModel {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "todo_text")
	private String todoText;

	@Column(name = "todo_is_completed")
	private boolean todoIsCompleted;

	public TodoModel() {

	}
	
	public TodoModel(String todo_text, boolean todo_is_completed) {
		this.todoText = todo_text;
		this.todoIsCompleted = todo_is_completed;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTodoText() {
		return todoText;
	}

	public void setTodoText(String todoText) {
		this.todoText = todoText;
	}

	public boolean isTodoIsCompleted() {
		return todoIsCompleted;
	}

	public void setTodoIsCompleted(boolean todoIsCompleted) {
		this.todoIsCompleted = todoIsCompleted;
	}

	@Override
	public String toString() {
		return "TodoModel [id=" + id + ", todoText=" + todoText + ", todoIsCompleted=" + todoIsCompleted + "]";
	}

	
}
