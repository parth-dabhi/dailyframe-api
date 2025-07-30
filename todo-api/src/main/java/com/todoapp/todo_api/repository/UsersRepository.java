package com.todoapp.todo_api.repository;

import com.todoapp.todo_api.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, String> {
}
