package com.todoapp.todo_api.dto;

import jakarta.validation.constraints.NotNull;

public class ListRequest {

    @NotNull
    private String listName;

    @NotNull
    private String userEmail; // This will be used to fetch the actual User

    // Getters & Setters
    public String getListName() { return listName; }
    public void setListName(String listName) { this.listName = listName; }

    public String getUserEmail() { return userEmail; }
    public void setUserEmail(String userEmail) { this.userEmail = userEmail; }
}
