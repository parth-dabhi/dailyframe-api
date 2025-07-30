package com.todoapp.todo_api.repository;

import com.todoapp.todo_api.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Long> {

}