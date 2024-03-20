package com.booleanuk.library.controllers;

import com.booleanuk.library.models.Muscle;
import com.booleanuk.library.payload.response.*;
import com.booleanuk.library.repository.MuscleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/muscles")
public class MuscleController {

    @Autowired
    private MuscleRepository muscleRepository;

    @GetMapping
    public ResponseEntity<MuscleListResponse> getAllItems() {
        MuscleListResponse response = new MuscleListResponse();
        response.set(muscleRepository.findAll());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<?>> getItemById(@PathVariable int id) {
        Muscle item = muscleRepository.findById(id).orElse(null);
        if (item == null) {
            ErrorResponse error = new ErrorResponse();
            error.set("Item not found");
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
        MuscleResponse itemResponse = new MuscleResponse();
        itemResponse.set(item);
        return ResponseEntity.ok(itemResponse);
    }

    @PostMapping
    public ResponseEntity<Response<?>> createItem(@RequestBody Muscle item) {
        MuscleResponse itemResponse = new MuscleResponse();
        try {
            itemResponse.set(muscleRepository.save(item));
        } catch (Exception e) {
            ErrorResponse error = new ErrorResponse();
            error.set("Bad request");
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(itemResponse, HttpStatus.CREATED);
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<Response<?>> updateItem(@PathVariable int id, @RequestBody Item itemDetails) {
//        Muscle item = muscleRepository.findById(id).orElse(null);
//        if (item == null) {
//            ErrorResponse error = new ErrorResponse();
//            error.set("Item not found");
//            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
//        }
//        // Update item details
//        item.setMuscleName(itemDetails.getName());
//        item.setMuscleGroup(itemDetails.getType());
//        MuscleResponse itemResponse = new MuscleResponse();
//        try {
//            itemResponse.set(muscleRepository.save(item));
//        } catch (Exception e) {
//            ErrorResponse error = new ErrorResponse();
//            error.set("Bad request");
//            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
//        }
//        return ResponseEntity.ok(itemResponse);
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response<?>> deleteItem(@PathVariable int id) {
        Muscle item = muscleRepository.findById(id).orElse(null);
        if (item == null) {
            ErrorResponse error = new ErrorResponse();
            error.set("Item not found");
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
        muscleRepository.delete(item);
        MuscleResponse itemResponse = new MuscleResponse();
        itemResponse.set(item);
        return ResponseEntity.ok(itemResponse);
    }
}

