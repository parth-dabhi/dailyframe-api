package com.todoapp.todo_api.dto;

import jakarta.validation.constraints.NotNull;

public class ListRequest {

    @NotNull
    private String listName;

    // Getters & Setters
    public String getListName() { return listName; }
    public void setListName(String listName) { this.listName = listName; }
}
