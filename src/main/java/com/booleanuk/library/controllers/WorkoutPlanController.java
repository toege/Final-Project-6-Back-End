package com.booleanuk.library.controllers;

import com.booleanuk.library.models.Exercise;
import com.booleanuk.library.models.Log;
import com.booleanuk.library.models.Rep;
import com.booleanuk.library.models.WorkoutPlan;
import com.booleanuk.library.payload.response.*;
import com.booleanuk.library.repository.LogRepository;
import com.booleanuk.library.repository.WorkoutPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/workoutplans")
public class WorkoutPlanController {

    @Autowired
    private WorkoutPlanRepository workoutPlanRepository;

    @Autowired
    private LogRepository logRepository;
    @GetMapping
    public ResponseEntity<WorkoutPlanListResponse> getAll() {
        WorkoutPlanListResponse response = new WorkoutPlanListResponse();
        response.set(workoutPlanRepository.findAll());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<?>> getById(@PathVariable int id) {
        WorkoutPlan workoutPlan = workoutPlanRepository.findById(id).orElse(null);
        if (workoutPlan == null) {
            ErrorResponse error = new ErrorResponse();
            error.set("Workout plan not found");
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
        WorkoutPlanResponse workoutPlanResponse = new WorkoutPlanResponse();
        workoutPlanResponse.set(workoutPlan);
        return ResponseEntity.ok(workoutPlanResponse);
    }

    @PostMapping
    public ResponseEntity<Response<?>> create(@RequestBody WorkoutPlan workoutPlan) {
        WorkoutPlanResponse workoutPlanResponse = new WorkoutPlanResponse();
        try {
            workoutPlanResponse.set(workoutPlanRepository.save(workoutPlan));
        } catch (Exception e) {
            ErrorResponse error = new ErrorResponse();
            error.set("Bad request");
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(workoutPlanResponse, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response<?>> update(@PathVariable int id, @RequestBody WorkoutPlan workoutPlanDetails) {
        WorkoutPlan workoutPlan = workoutPlanRepository.findById(id).orElse(null);
        if (workoutPlan == null) {
            ErrorResponse error = new ErrorResponse();
            error.set("Workout plan not found");
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
        workoutPlan.setNotes(workoutPlanDetails.getNotes());
        WorkoutPlanResponse workoutPlanResponse = new WorkoutPlanResponse();
        try {
            workoutPlanResponse.set(workoutPlanRepository.save(workoutPlan));
        } catch (Exception e) {
            ErrorResponse error = new ErrorResponse();
            error.set("Bad request");
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(workoutPlanResponse);
    }

    @PostMapping("/log/{logId}")
    public ResponseEntity<?> createWorkoutPlanWithLog(@PathVariable int logId, @RequestBody WorkoutPlan request) {
        Log log = logRepository.findById(logId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Log not found"));

        WorkoutPlan newWorkoutPlan = new WorkoutPlan();
        newWorkoutPlan.setLog(log);
        newWorkoutPlan.setNotes(request.getNotes());
        newWorkoutPlan.setExercises(request.getExercises()); // Handle with care, see notes below

        WorkoutPlan savedWorkoutPlan = workoutPlanRepository.save(newWorkoutPlan);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedWorkoutPlan);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Response<?>> delete(@PathVariable int id) {
        WorkoutPlan workoutPlan = workoutPlanRepository.findById(id).orElse(null);
        if (workoutPlan == null) {
            ErrorResponse error = new ErrorResponse();
            error.set("Workout plan not found");
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
        workoutPlanRepository.delete(workoutPlan);
        WorkoutPlanResponse messageResponse = new WorkoutPlanResponse();
        messageResponse.set(workoutPlan);
        return ResponseEntity.ok(messageResponse);
    }
}
