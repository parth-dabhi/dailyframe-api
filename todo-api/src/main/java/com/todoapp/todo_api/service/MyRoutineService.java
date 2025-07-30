package com.todoapp.todo_api.service;

import com.todoapp.todo_api.dto.MyRoutineRequest;
import com.todoapp.todo_api.entity.MyRoutine;
import com.todoapp.todo_api.entity.Users;
import com.todoapp.todo_api.exceptions.RoutineNotFoundException;
import com.todoapp.todo_api.repository.MyRoutineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("myRoutineService")
public class MyRoutineService {
    @Autowired
    private MyRoutineRepository myRoutineRepository;

    public void createDefaultMyRoutine(Users user) {
        // Create default routine for the user
        myRoutineRepository.save(createMyRoutineInstance(user));
    }

    public MyRoutine createMyRoutineInstance(Users user) {
        MyRoutine newMyRoutine = new MyRoutine();
        newMyRoutine.setUser(user);
        return newMyRoutine;
    }

    public List<MyRoutine> getAllMyRoutinesOfUser(String email) {
        return myRoutineRepository.findAllByUserEmail(email);
    }

    public MyRoutine createNewRoutineOfUser(String email, MyRoutineRequest myRoutineRequest, Users user) {
        MyRoutine newMyRoutine = new MyRoutine();
        newMyRoutine.setTitle(myRoutineRequest.getTitle());
        newMyRoutine.setDescription(myRoutineRequest.getDescription());
        newMyRoutine.setStartTime(myRoutineRequest.getStartTime());
        newMyRoutine.setEndTime(myRoutineRequest.getEndTime());
        newMyRoutine.setStatus(myRoutineRequest.getStatus());
        newMyRoutine.setUser(user);

        return myRoutineRepository.save(newMyRoutine);
    }

    public MyRoutine updateMyRoutine(Long id, MyRoutineRequest myRoutineRequest) {
        MyRoutine myRoutine = validateRoutine(id).get();

        myRoutine.setTitle(myRoutineRequest.getTitle());
        myRoutine.setDescription(myRoutineRequest.getDescription());
        myRoutine.setStartTime(myRoutineRequest.getStartTime());
        myRoutine.setEndTime(myRoutineRequest.getEndTime());
        myRoutine.setStatus(myRoutineRequest.getStatus());

        myRoutineRepository.save(myRoutine);

        return myRoutine;
    }

    public Optional<MyRoutine> validateRoutine(Long id) {
        Optional<MyRoutine> myRoutine = myRoutineRepository.findById(id);
        if (myRoutine.isEmpty()) {
            throw new RoutineNotFoundException("Routine not found with id : " + id);
        }
        return myRoutine;
    }

    public void deleteRoutine(Long id) {
        validateRoutine(id);
        myRoutineRepository.deleteById(id);
    }
}
