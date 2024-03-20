package com.booleanuk.library.controllers;

import com.booleanuk.library.models.Log;
import com.booleanuk.library.models.User;
import com.booleanuk.library.payload.response.*;
import com.booleanuk.library.repository.LogRepository;
import com.booleanuk.library.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/logs")
public class LogController {
    @Autowired
    private LogRepository logRepository;

    @Autowired
    private UserRepository userRepository;
    @GetMapping
    public ResponseEntity<LogListResponse> getAll() {
        LogListResponse response = new LogListResponse();
        response.set(logRepository.findAll());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<LogListResponse> getByUserId(@PathVariable int userId) {
        LogListResponse response = new LogListResponse();
        response.set(logRepository.findByUserId(userId));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<?>> getById(@PathVariable int id) {
        Log log = logRepository.findById(id).orElse(null);
        LogResponse logResponse = new LogResponse();
        logResponse.set(log);
        return ResponseEntity.ok(logResponse);
    }

    @PostMapping("user/{userId}")
    public ResponseEntity<Response<?>> createTicket(@PathVariable int userId, @RequestBody Log request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        Log ticket = new Log();
        ticket.setUser(user);
        ticket.setLogDat(request.getLogDat());
        Log savedTicket = logRepository.save(ticket);
        LogResponse logResponse = new LogResponse();
        logResponse.set(savedTicket);
        return new ResponseEntity<>(logResponse, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        Log log = logRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Log not found"));
        logRepository.delete(log);
        return ResponseEntity.noContent().build();
    }

}
