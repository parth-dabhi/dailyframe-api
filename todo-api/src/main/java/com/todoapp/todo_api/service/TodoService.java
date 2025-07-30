package com.todoapp.todo_api.service;

import com.todoapp.todo_api.dto.TodoRequest;
import com.todoapp.todo_api.entity.Lists;
import com.todoapp.todo_api.entity.Todo;
import com.todoapp.todo_api.exceptions.TodoNotFoundException;
import com.todoapp.todo_api.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service("todoService")
public class TodoService {

    private final TodoRepository todoRepository;
    private final ListsService listsService;

    public TodoService(TodoRepository todoRepository, ListsService listsService) {
        this.todoRepository = todoRepository;
        this.listsService = listsService;
    }

    public Todo createNewTodoInList(TodoRequest request) {
        return todoRepository.save(createTodoInstance(request.getDescription(), request.getTargetDate(),
                            listsService.validateList(request.getListId()).get()));
    }

    public Todo createTodoInstance(String task, LocalDate targetDate, Lists list) {
        Todo newTodo = new Todo();
        newTodo.setDescription(task);
        newTodo.setTargetDate(targetDate);
        newTodo.setList(list);
        return newTodo;
    }

    public void deleteTodo(Long id) {
        validateTodoId(id);
        todoRepository.deleteById(id);
    }

    private Optional<Todo> validateTodoId(Long id) {
        Optional<Todo> todo = todoRepository.findById(id);
        if (todo.isEmpty()) {
            throw new TodoNotFoundException("Todo not found with ID : " + id);
        }
        return todo;
    }


    public Todo updateTodo(Long id, TodoRequest todoRequest) {
        Todo todo = validateTodoId(id).get();

        todo.setDescription(todoRequest.getDescription());
        todo.setTargetDate(todoRequest.getTargetDate());
        todo.setStatus(todoRequest.getStatus());

        todoRepository.save(todo);
        return todo;
    }
}
