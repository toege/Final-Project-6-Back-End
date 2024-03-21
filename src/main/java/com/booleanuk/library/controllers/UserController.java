package com.booleanuk.library.controllers;

import com.booleanuk.library.models.User;
import com.booleanuk.library.payload.response.ErrorResponse;
import com.booleanuk.library.payload.response.Response;
import com.booleanuk.library.payload.response.UserListResponse;
import com.booleanuk.library.payload.response.UserResponse;
import com.booleanuk.library.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public ResponseEntity<UserListResponse> getAllUsers() {
        UserListResponse response = new UserListResponse();
        response.set(userRepository.findAll());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<?>> getUserById(@PathVariable int id) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            ErrorResponse error = new ErrorResponse();
            error.set("User not found");
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
        UserResponse userResponse = new UserResponse();
        userResponse.set(user);
        return ResponseEntity.ok(userResponse);
    }

    @PostMapping
    public ResponseEntity<Response<?>> createUser(@RequestBody User user) {
        UserResponse userResponse = new UserResponse();
        try {
            userResponse.set(userRepository.save(user));
        } catch (Exception e) {
            ErrorResponse error = new ErrorResponse();
            error.set("Bad request");
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response<?>> updateUser(@PathVariable int id, @RequestBody User userDetails) {
        User existingUser = userRepository.findById(id).orElse(null);
        if (existingUser == null) {
            ErrorResponse error = new ErrorResponse();
            error.set("User not found");
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
        existingUser.setUsername(userDetails.getUsername());
        existingUser.setEmail(userDetails.getEmail());
        existingUser.setPassword(existingUser.getPassword());
        existingUser.setRoles(existingUser.getRoles());
        existingUser.setLogs(existingUser.getLogs());

        UserResponse userResponse = new UserResponse();
        try {
            userResponse.set(userRepository.save(existingUser));
        } catch (Exception e) {
            ErrorResponse error = new ErrorResponse();
            error.set("Error updating user: " + e.getMessage());
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(userResponse);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Response<?>> deleteUser(@PathVariable int id) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            ErrorResponse error = new ErrorResponse();
            error.set("User not found");
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
        userRepository.delete(user);
        UserResponse userResponse = new UserResponse();
        userResponse.set(user);
        return ResponseEntity.ok(userResponse);
    }
}
