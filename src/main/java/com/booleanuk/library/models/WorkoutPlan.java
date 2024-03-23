package com.booleanuk.library.models;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
@Entity
@Table(name = "workoutplans")
public class WorkoutPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "logDat")
    private LocalDateTime logDat;

    @Column(name = "notes")
    private String notes;


    @OneToMany(mappedBy = "workoutplan", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIncludeProperties(value = {"id", "workoutName", "reps", "sets", "expectedReps", "expectedSets", "weight"})
    private List<Exercise> exercises;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonIncludeProperties(value = {"id", "username", "email"})
    private User user;

//    @ManyToOne
//    @JoinColumn(name = "log_id", referencedColumnName = "id")
//    private Log log;
}
