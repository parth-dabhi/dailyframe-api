package com.todoapp.todo_api.controller;

import com.todoapp.todo_api.dto.ListRequest;
import com.todoapp.todo_api.entity.Lists;
import com.todoapp.todo_api.entity.Todo;
import com.todoapp.todo_api.entity.Users;
import com.todoapp.todo_api.repository.ListsRepository;
import com.todoapp.todo_api.repository.UsersRepository;
import com.todoapp.todo_api.service.ListsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ListsController {

    private final ListsService listsService;

    public ListsController(ListsService listsService) {
        this.listsService = listsService;
    }

    @PostMapping("/lists")
    public ResponseEntity<Lists> createList(@RequestBody @Valid ListRequest request) {
        Lists responseList = listsService.createNewList(request);
        return ResponseEntity.ok(responseList);
    }

    @GetMapping("/lists/{listId}/todos")
    public ResponseEntity<List<Todo>> getAllTodosInList(@PathVariable Long listId) {
        return listsService.getAllTodosInList(listId);
    }
}
