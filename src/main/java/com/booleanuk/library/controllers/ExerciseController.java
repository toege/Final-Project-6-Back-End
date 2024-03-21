package com.booleanuk.library.controllers;

import com.booleanuk.library.models.Exercise;
import com.booleanuk.library.models.Rep;
import com.booleanuk.library.models.WorkoutPlan;
import com.booleanuk.library.payload.response.*;
import com.booleanuk.library.repository.ExerciseRepository;
import com.booleanuk.library.repository.WorkoutPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/exercises")
public class ExerciseController {
    @Autowired
    private ExerciseRepository exerciseRepository;

    @Autowired
    private WorkoutPlanRepository workoutPlanRepository;

    @GetMapping
    public ResponseEntity<ExerciseListResponse> getAll() {
        ExerciseListResponse response = new ExerciseListResponse();
        response.set(exerciseRepository.findAll());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<?>> getById(@PathVariable int id) {
        Exercise exercise = exerciseRepository.findById(id).orElse(null);
        if (exercise == null) {
            ErrorResponse error = new ErrorResponse();
            error.set("Exercise not found");
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
        ExerciseResponse exerciseResponse = new ExerciseResponse();
        exerciseResponse.set(exercise);
        return ResponseEntity.ok(exerciseResponse);
    }

    @PostMapping
    public ResponseEntity<Response<?>> create(@RequestBody Exercise exercise) {
        ExerciseResponse exerciseResponse = new ExerciseResponse();
        try {
            exerciseResponse.set(exerciseRepository.save(exercise));
        } catch (Exception e) {
            ErrorResponse error = new ErrorResponse();
            error.set("Bad request");
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(exerciseResponse, HttpStatus.CREATED);
    }

    @PostMapping("workoutplan/{workoutplanId}")
    public ResponseEntity<Response<?>> createExercise(@PathVariable int workoutplanId, @RequestBody Exercise request) {
        WorkoutPlan plan = workoutPlanRepository.findById(workoutplanId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        Exercise ticket = new Exercise();
//        ticket.setWorkoutplan(plan);
        ticket.setWorkoutName(request.getWorkoutName());
        ticket.setSets(request.getSets());
        ticket.setExpectedSets(request.getExpectedSets());
        ticket.setExpectedReps(request.getExpectedReps());
        Exercise savedTicket = exerciseRepository.save(ticket);
        ExerciseResponse logResponse = new ExerciseResponse();
        logResponse.set(savedTicket);
        return new ResponseEntity<>(logResponse, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response<?>> update(@PathVariable int id, @RequestBody Exercise exerciseDetails) {
        Exercise exercise = exerciseRepository.findById(id).orElse(null);
        if (exercise == null) {
            ErrorResponse error = new ErrorResponse();
            error.set("Exercise not found");
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
        // Update exercise details
        exercise.setWorkoutName(exerciseDetails.getWorkoutName());
        exercise.setSets(exerciseDetails.getSets());
        exercise.setExpectedReps(exerciseDetails.getExpectedReps());
        exercise.setExpectedSets(exerciseDetails.getExpectedSets());
        // Consider handling updates to reps and workout plan here

        ExerciseResponse exerciseResponse = new ExerciseResponse();
        try {
            exerciseResponse.set(exerciseRepository.save(exercise));
        } catch (Exception e) {
            ErrorResponse error = new ErrorResponse();
            error.set("Bad request");
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(exerciseResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response<?>> delete(@PathVariable int id) {
        Exercise exercise = exerciseRepository.findById(id).orElse(null);
        if (exercise == null) {
            ErrorResponse error = new ErrorResponse();
            error.set("Exercise not found");
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
        exerciseRepository.delete(exercise);
        ExerciseResponse messageResponse = new ExerciseResponse();
        messageResponse.set(exercise);
        return ResponseEntity.ok(messageResponse);
    }
}
