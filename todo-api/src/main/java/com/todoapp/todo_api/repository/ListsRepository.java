package com.todoapp.todo_api.repository;

import com.todoapp.todo_api.entity.Lists;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ListsRepository extends JpaRepository<Lists, Long> {

    List<Lists> findAllByUserEmail(String email);
}
