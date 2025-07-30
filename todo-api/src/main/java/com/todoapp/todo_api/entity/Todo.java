package com.todoapp.todo_api.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import org.hibernate.mapping.List;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "todos")
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long todoId;

    @Column
    @NotNull
    private String description;

    @Column
    @FutureOrPresent
    @NotNull
    private LocalDate targetDate;

    @Column(nullable = false)
    private boolean status = false;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "list_id", nullable = false) // Foreign Key to TaskList
    @JsonIgnore
    private Lists list; // Many Todos -> One Task List

    // Constructors, Getters, and Setters
    public Todo() {}

    public Todo(Long todoId, String description, LocalDate targetDate, boolean status, LocalDateTime createdAt, Lists list) {
        this.todoId = todoId;
        this.description = description;
        this.targetDate = targetDate;
        this.status = status;
        this.createdAt = createdAt;
        this.list = list;
    }

    public Long getTodoId() {
        return todoId;
    }

    public void setTodoId(Long todoId) {
        this.todoId = todoId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getTargetDate() {
        return targetDate;
    }

    public void setTargetDate(LocalDate targetDate) {
        this.targetDate = targetDate;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Lists getList() {
        return list;
    }

    public void setList(Lists list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "Todo{" +
                "todoId=" + todoId +
                ", description='" + description + '\'' +
                ", targetDate=" + targetDate +
                ", status=" + status +
                ", createdAt=" + createdAt +
                ", list=" + list +
                '}';
    }
}

