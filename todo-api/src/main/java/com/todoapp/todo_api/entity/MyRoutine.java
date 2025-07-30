package com.todoapp.todo_api.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalTime;

@Entity
@Table(name = "my_routine")
public class MyRoutine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long routineId;

    @Column
    @NotNull
    private String title = "Untitled Task"; // Default title if not provided

    @Column
    private String description = "Give Description Here!"; // Default description if not provided

    @Column
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm") // Format time properly
    private LocalTime startTime = LocalTime.now(); // Default start time if not provided

    @Column
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private LocalTime endTime = LocalTime.now().plusHours(1); // Default end time if not provided

    @Column(nullable = false)
    private boolean status = false;

    @ManyToOne
    @JoinColumn(name = "user_email", nullable = false) // Foreign Key to User
    @JsonIgnore
    private Users user; // One Routine → One Task List

    // Constructors, Getters, and Setters

    public MyRoutine() {}

    public MyRoutine(Long routineId, String title, String description, LocalTime startTime, LocalTime endTime, boolean status, Users user) {
        this.routineId = routineId;
        this.title = title;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
        this.user = user;
    }

    public Long getRoutineId() {
        return routineId;
    }

    public void setRoutineId(Long routineId) {
        this.routineId = routineId;
    }

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

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "MyRoutine{" +
                "routineId=" + routineId +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", status=" + status +
                ", user=" + user +
                '}';
    }
}
