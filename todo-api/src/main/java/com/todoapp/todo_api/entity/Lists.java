package com.todoapp.todo_api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "task_lists")
public class Lists {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-Incremented
    private Long listId;

    @Column(nullable = false)
    private String listName;

    @Column(nullable = false, updatable = false)
    private LocalDateTime dateCreated = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "user_email", nullable = false) // Foreign Key to User
    @JsonIgnore
    private Users user; // Many Task Lists → One User

    @OneToMany(mappedBy = "list")
    private List<Todo> todos; // One Task List → Many Todos

    // Constructors, Getters, and Setters

    public Lists() {}

    public Lists(Long listId, String listName, LocalDateTime dateCreated, Users user, List<Todo> todos) {
        this.listId = listId;
        this.listName = listName;
        this.dateCreated = dateCreated;
        this.user = user;
        this.todos = todos;
    }

    public Long getListId() {
        return listId;
    }

    public void setListId(Long listId) {
        this.listId = listId;
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public List<Todo> getTodos() {
        return todos;
    }

    public void setTodos(List<Todo> todos) {
        this.todos = todos;
    }

    @Override
    public String toString() {
        return "Lists{" +
                "listId=" + listId +
                ", listName='" + listName + '\'' +
                ", dateCreated=" + dateCreated +
                ", user=" + user +
                ", todos=" + todos +
                '}';
    }
}