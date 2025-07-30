package com.todoapp.todo_api.controller;

import com.todoapp.todo_api.dto.TodoRequest;
import com.todoapp.todo_api.entity.Lists;
import com.todoapp.todo_api.entity.Todo;
import com.todoapp.todo_api.repository.ListsRepository;
import com.todoapp.todo_api.repository.TodoRepository;
import com.todoapp.todo_api.service.TodoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class TodoController {

    @Autowired
    private TodoService todoService;

    @PostMapping("/todos")
    public ResponseEntity<Todo> createList(@RequestBody @Valid TodoRequest request) {
        Todo responseTodo = todoService.createNewTodoInList(request);
        return ResponseEntity.ok(responseTodo);
    }

    @DeleteMapping("/todos/{id}")
    public ResponseEntity<String> deleteTodo(@PathVariable Long id) {
        todoService.deleteTodo(id);
        return ResponseEntity.ok("Todo deleted successfully");
    }

    @PutMapping("/todos/{id}")
    public ResponseEntity<Todo> updateTodo(@PathVariable Long id, @RequestBody TodoRequest todoRequest) {
        return ResponseEntity.ok(todoService.updateTodo(id, todoRequest));
    }

}
