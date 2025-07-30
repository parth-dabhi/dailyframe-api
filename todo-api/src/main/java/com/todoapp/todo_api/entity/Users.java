package com.todoapp.todo_api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
@Table(name = "users")
public class Users {

    @Id
    @Email
    @NotNull
    private String email; // Primary Key (Unique ID)

    @Column(nullable = false)
    @NotNull
    @Size(min = 6, message = "Password must be at least 6 characters long")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Column(nullable = false, length = 50)
    @NotNull
    private String username;

    @OneToMany(mappedBy = "user")
    private List<Lists> lists; // One User -> Many Task Lists

    @OneToMany(mappedBy = "user")
    private List<MyRoutine> myRoutine; // One User -> One MyRoutine

    // Constructors, Getters, and Setters
    public Users() {}

    public Users(String email, String password, String username, List<Lists> lists, List<MyRoutine> myRoutine) {
        this.email = email;
        this.password = password;
        this.username = username;
        this.lists = lists;
        this.myRoutine = myRoutine;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Lists> getLists() {
        return lists;
    }

    public void setLists(List<Lists> lists) {
        this.lists = lists;
    }

    public List<MyRoutine> getMyRoutine() {
        return myRoutine;
    }

    public void setMyRoutine(List<MyRoutine> myRoutine) {
        this.myRoutine = myRoutine;
    }

    @Override
    public String toString() {
        return "Users{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", username='" + username + '\'' +
                ", lists=" + lists +
                ", myRoutine=" + myRoutine +
                '}';
    }
}
