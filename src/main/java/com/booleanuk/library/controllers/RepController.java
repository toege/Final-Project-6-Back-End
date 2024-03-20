package com.booleanuk.library.controllers;

import com.booleanuk.library.models.Exercise;
import com.booleanuk.library.models.Log;
import com.booleanuk.library.models.Rep;
import com.booleanuk.library.models.User;
import com.booleanuk.library.payload.response.*;
import com.booleanuk.library.repository.ExerciseRepository;
import com.booleanuk.library.repository.RepRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/reps")
public class RepController {

    @Autowired
    private RepRepository repRepository;

    @Autowired
    private ExerciseRepository exerciseRepository;

    @GetMapping
    public ResponseEntity<RepListResponse> getAllItems() {
        RepListResponse response = new RepListResponse();
        response.set(repRepository.findAll());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<?>> getItemById(@PathVariable int id) {
        Rep item = repRepository.findById(id).orElse(null);
        if (item == null) {
            ErrorResponse error = new ErrorResponse();
            error.set("Item not found");
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
        RepResponse itemResponse = new RepResponse();
        itemResponse.set(item);
        return ResponseEntity.ok(itemResponse);
    }

    @PostMapping
    public ResponseEntity<Response<?>> createItem(@RequestBody Rep item) {
        RepResponse itemResponse = new RepResponse();
        try {
            itemResponse.set(repRepository.save(item));
        } catch (Exception e) {
            ErrorResponse error = new ErrorResponse();
            error.set("Bad request");
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(itemResponse, HttpStatus.CREATED);
    }

    @PostMapping("exercise/{exerciseId}")
    public ResponseEntity<Response<?>> createTicket(@PathVariable int exerciseId, @RequestBody Rep request) {
        Exercise exercise = exerciseRepository.findById(exerciseId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        Rep ticket = new Rep();
        ticket.setExercise(exercise);
        ticket.setReps(request.getReps());
        ticket.setReps(request.getWeight());
        Rep savedTicket = repRepository.save(ticket);
        RepResponse logResponse = new RepResponse();
        logResponse.set(savedTicket);
        return new ResponseEntity<>(logResponse, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Response<?>> updateItem(@PathVariable int id, @RequestBody Rep itemDetails) {
        Rep item = repRepository.findById(id).orElse(null);
        if (item == null) {
            ErrorResponse error = new ErrorResponse();
            error.set("Item not found");
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
        // Update item details
        item.setReps(itemDetails.getReps());
        item.setWeight(itemDetails.getWeight());
        // You might need to set other properties, depending on the design of your Exercise entity
        RepResponse itemResponse = new RepResponse();
        try {
            itemResponse.set(repRepository.save(item));
        } catch (Exception e) {
            ErrorResponse error = new ErrorResponse();
            error.set("Bad request");
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(itemResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response<?>> deleteItem(@PathVariable int id) {
        Rep item = repRepository.findById(id).orElse(null);
        if (item == null) {
            ErrorResponse error = new ErrorResponse();
            error.set("Item not found");
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
        repRepository.delete(item);
        RepResponse itemResponse = new RepResponse();
        itemResponse.set(item);
        return ResponseEntity.ok(itemResponse);
    }
}
