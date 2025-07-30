package com.todoapp.todo_api.controller;

import com.todoapp.todo_api.dto.MyRoutineRequest;
import com.todoapp.todo_api.entity.Lists;
import com.todoapp.todo_api.entity.MyRoutine;
import com.todoapp.todo_api.entity.Users;
import com.todoapp.todo_api.jwt.JwtTokenResponse;
import com.todoapp.todo_api.service.UsersService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UsersController {

    private final UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping("/users/me")
    public ResponseEntity<Users> getUser(Authentication authentication) {
        String email = authentication.getName();
        Users user = usersService.findById(email);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/users")
    public ResponseEntity<?> createUser(@RequestBody @Valid Users user) {
        try {
            String jwtToken = usersService.saveNewUser(user);
            return ResponseEntity.ok(new JwtTokenResponse(jwtToken));
        } catch (RuntimeException e) {
            // 409
            return ResponseEntity.status(HttpStatus.CONFLICT).body("User already exists");
        }
    }

    @GetMapping("/users/lists")
    public ResponseEntity<List<Lists>> getUserLists(Authentication authentication) {
        String email = authentication.getName();
        return ResponseEntity.ok(usersService.retrieveAllListOfUser(email));
    }

    @GetMapping("/users/routines")
    public ResponseEntity<List<MyRoutine>> getAllMyRoutines(Authentication authentication) {
        String email = authentication.getName();
        return ResponseEntity.ok(usersService.getAllMyRoutines(email));
    }
    @PostMapping("/users/routines")
    public ResponseEntity<MyRoutine> createNewRoutines
            (Authentication authentication, @RequestBody MyRoutineRequest myRoutineRequest) {
        String email = authentication.getName();
        MyRoutine responseMyRoutine = usersService.createNewRoutine(email, myRoutineRequest);
        return ResponseEntity.ok(responseMyRoutine);
    }
}