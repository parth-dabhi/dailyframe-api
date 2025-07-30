package com.todoapp.todo_api.dto;

import com.todoapp.todo_api.entity.MyRoutine;
import jakarta.validation.constraints.NotNull;

import java.time.LocalTime;

public class MyRoutineRequest {

    @NotNull
    private String title; // Default title if not provided

    @NotNull
    private String description; // Default description if not provided

    @NotNull
    private LocalTime startTime; // Default start time if not provided

    @NotNull
    private LocalTime endTime; // Default end time if not provided

    private boolean status = false;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
