package com.booleanuk.library.models;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@Entity
@Table(name = "workoutplans")
public class WorkoutPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "notes")
    private String notes;

    @OneToMany(mappedBy = "workoutplan", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIncludeProperties(value = {"id", "workoutName", "reps", "sets", "expectedReps", "expectedSets"})
    private List<Exercise> exercises;

    @ManyToOne
    @JoinColumn(name = "log_id", referencedColumnName = "id")
    private Log log;
}
