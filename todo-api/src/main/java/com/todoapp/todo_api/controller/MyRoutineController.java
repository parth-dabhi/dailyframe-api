package com.todoapp.todo_api.controller;

import com.todoapp.todo_api.dto.MyRoutineRequest;
import com.todoapp.todo_api.entity.MyRoutine;
import com.todoapp.todo_api.service.MyRoutineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/routines")
public class MyRoutineController {

    private final MyRoutineService myRoutineService;

    public MyRoutineController(MyRoutineService myRoutineService) {
        this.myRoutineService = myRoutineService;
    }

    @PutMapping("/{id}")
    public ResponseEntity<MyRoutine> updateMyRoutine(@PathVariable Long id, @RequestBody MyRoutineRequest myRoutineRequest) {
        return ResponseEntity.ok(myRoutineService.updateMyRoutine(id, myRoutineRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRoutine(@PathVariable Long id) {
        myRoutineService.deleteRoutine(id);
        return ResponseEntity.ok("Routine deleted successfully");
    }
}
