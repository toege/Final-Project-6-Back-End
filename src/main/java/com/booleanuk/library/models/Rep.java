package com.booleanuk.library.models;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "reps")
public class Rep {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "reps")
    private String reps;

    @Column(name = "weight")
    private String weight;

//    @ManyToOne
//    @JoinColumn(name = "exercise_id")
//    @JsonIncludeProperties(value = {"id", "workoutName", "sets", "expectedReps", "expectedSets"})
//    private Exercise exercise;

    public Rep(String reps, String weight, Exercise exercise) {
        this.reps = reps;
        this.weight = weight;
//        this.exercise = exercise;
    }
}
