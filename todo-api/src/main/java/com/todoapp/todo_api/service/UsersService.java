package com.todoapp.todo_api.service;

import com.todoapp.todo_api.dto.MyRoutineRequest;
import com.todoapp.todo_api.entity.Lists;
import com.todoapp.todo_api.entity.MyRoutine;
import com.todoapp.todo_api.entity.Users;
import com.todoapp.todo_api.exceptions.UserNotFoundException;
import com.todoapp.todo_api.jwt.JwtTokenService;
import com.todoapp.todo_api.repository.UsersRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("usersService")
public class UsersService {

    private final UsersRepository usersRepository;
    private final ListsService listsService;
    private final MyRoutineService myRoutineService;
    private final JwtTokenService jwtTokenService;
    private final AuthenticationManager authenticationManager;

    private final PasswordEncoder passwordEncoder;

    public UsersService(UsersRepository usersRepository, ListsService listsService, MyRoutineService myRoutinService, JwtTokenService jwtTokenService, AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.listsService = listsService;
        this.myRoutineService = myRoutinService;
        this.jwtTokenService = jwtTokenService;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
    }

    public Users findById(String email) {
        Optional<Users> user = validateUser(email);
        return user.get();
    }

    public Optional<Users> validateUser(String email) {
        Optional<Users> user = usersRepository.findById(email);
        if (user.isEmpty()) {
            throw new UserNotFoundException("User not found with email : " + email);
        }
        return user;
    }

    public String saveNewUser(Users user) {
        if(usersRepository.findById(user.getEmail()).isPresent()){
            throw new RuntimeException("User already exists!");
        }

        // Store the raw password before encoding
        String rawPassword = user.getPassword();

        // Encode the password before saving
        user.setPassword(passwordEncoder.encode(rawPassword));
        System.out.println("Encoded Password = " + user.getPassword());

        usersRepository.save(user);

        myRoutineService.createDefaultMyRoutine(user);

        System.out.println("Authenticating with raw password: " + rawPassword);

        // Authenticate the user - // Use raw password for authentication
        var authenticationToken = new UsernamePasswordAuthenticationToken(user.getEmail(), rawPassword);
        var authentication = authenticationManager.authenticate(authenticationToken);

        // Generate JWT token after successful registration
        return jwtTokenService.generateToken(authentication);
    }

    public List<Lists> retrieveAllListOfUser(String email) {
        validateUser(email);
        return listsService.getAllLists(email);
    }

    public List<MyRoutine> getAllMyRoutines(String email) {
        validateUser(email);
        return myRoutineService.getAllMyRoutinesOfUser(email);
    }

    public MyRoutine createNewRoutine(String email, MyRoutineRequest myRoutineRequest) {
        Users user = validateUser(email).get();
        return myRoutineService.createNewRoutineOfUser(email, myRoutineRequest, user);
    }
}
