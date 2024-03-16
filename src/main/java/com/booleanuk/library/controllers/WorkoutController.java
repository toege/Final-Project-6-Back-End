package com.booleanuk.library.controllers;
import com.booleanuk.library.models.Workout;
import com.booleanuk.library.payload.response.*;
import com.booleanuk.library.repository.WorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/workouts")
public class WorkoutController {
    @Autowired
    private WorkoutRepository workoutRepository;

    @GetMapping
    public ResponseEntity<WorkoutListResponse> getAllLoans() {
        WorkoutListResponse response = new WorkoutListResponse();
        response.set(workoutRepository.findAll());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<?>> getLoanById(@PathVariable int id) {
        Workout loan = workoutRepository.findById(id).orElse(null);
        if (loan == null) {
            ErrorResponse error = new ErrorResponse();
            error.set("Loan not found");
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
        WorkoutResponse loanResponse = new WorkoutResponse();
        loanResponse.set(loan);
        return ResponseEntity.ok(loanResponse);
    }

    @PostMapping
    public ResponseEntity<Response<?>> createLoan(@RequestBody Workout loan) {
        WorkoutResponse loanResponse = new WorkoutResponse();
        try {
            loanResponse.set(workoutRepository.save(loan));
        } catch (Exception e) {
            ErrorResponse error = new ErrorResponse();
            error.set("Bad request");
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(loanResponse, HttpStatus.CREATED);
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<Response<?>> updateLoan(@PathVariable int id, @RequestBody Workout loanDetails) {
//        Workout loan = workoutRepository.findById(id).orElse(null);
//        if (loan == null) {
//            ErrorResponse error = new ErrorResponse();
//            error.set("Loan not found");
//            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
//        }
//        // Update loan details
//        loan.setReturnDate(loanDetails.getReturnDate());
//        loan.setStatus(loanDetails.getStatus());
//        WorkoutResponse loanResponse = new WorkoutResponse();
//        try {
//            loanResponse.set(workoutRepository.save(loan));
//        } catch (Exception e) {
//            ErrorResponse error = new ErrorResponse();
//            error.set("Bad request");
//            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
//        }
//        return ResponseEntity.ok(loanResponse);
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response<?>> deleteLoan(@PathVariable int id) {
        Workout loan = workoutRepository.findById(id).orElse(null);
        if (loan == null) {
            ErrorResponse error = new ErrorResponse();
            error.set("Loan not found");
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
        workoutRepository.delete(loan);
        WorkoutResponse loanResponse = new WorkoutResponse();
        loanResponse.set(loan);
        return ResponseEntity.ok(loanResponse);
    }
}
