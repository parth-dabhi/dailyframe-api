package com.todoapp.todo_api.repository;

import com.todoapp.todo_api.entity.MyRoutine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MyRoutineRepository extends JpaRepository<MyRoutine, Long> {
    List<MyRoutine> findAllByUserEmail(String email);
}
