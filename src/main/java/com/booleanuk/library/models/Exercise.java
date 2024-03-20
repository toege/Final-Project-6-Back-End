package com.booleanuk.library.models;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "exercises")
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "workoutName")
    private String workoutName;
    @Column(name = "sets")
    private String sets;
    @Column(name = "expectedReps")
    private String expectedReps;
    @Column(name = "expectedSets")
    private String expectedSets;

    @OneToMany(mappedBy = "exercise", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIncludeProperties(value = {"id", "reps", "weight"})
    private List<Rep> reps;

    @ManyToOne
    @JoinColumn(name = "workoutplan_id", referencedColumnName = "id")
    @JsonIncludeProperties(value = {"id", "notes"})
    private WorkoutPlan workoutplan;


}