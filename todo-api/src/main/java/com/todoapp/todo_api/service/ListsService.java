package com.todoapp.todo_api.service;

import com.todoapp.todo_api.dto.ListRequest;
import com.todoapp.todo_api.entity.Lists;
import com.todoapp.todo_api.entity.Todo;
import com.todoapp.todo_api.entity.Users;
import com.todoapp.todo_api.exceptions.ListNotFoundException;
import com.todoapp.todo_api.exceptions.UserNotFoundException;
import com.todoapp.todo_api.repository.ListsRepository;
import com.todoapp.todo_api.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("listsService")
public class ListsService {

    @Autowired
    private ListsRepository listsRepository;
    @Autowired
    private UsersRepository usersRepository;

    public String getCurrentUserEmail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName(); // this will be the email or username
    }

    public List<Lists> getAllLists(String email) {
        return listsRepository.findAllByUserEmail(email);
    }

    public Lists createNewList(ListRequest request) {
        String userEmail = getCurrentUserEmail();
        Optional<Users> user = usersRepository.findById(userEmail);
        if (user.isEmpty()) {
            throw new UserNotFoundException("User not found with email : " + userEmail);
        }

        return  listsRepository.save(createListInstance(request.getListName(), user.get()));
    }

    public Lists createListInstance(String listName, Users user) {
        Lists newList = new Lists();
        newList.setListName(listName);
        newList.setUser(user);
        return newList;
    }

    public ResponseEntity<List<Todo>> getAllTodosInList(Long listId) {
        return ResponseEntity.ok(validateList(listId).get().getTodos());
    }

    public Optional<Lists> validateList(Long listId) {
        Optional<Lists> list = listsRepository.findById(listId);
        if (list.isEmpty()) {
            throw new ListNotFoundException("List not found with ID : " + listId);
        }
        return list;
    }
}
