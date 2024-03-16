package com.booleanuk.library.models;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity
@Table(name = "workouts")
public class Workout {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "workoutName")
    private String workoutName;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "muscle_id", referencedColumnName = "id")
    @JsonIncludeProperties(value = {"id", "muscleName", "muscleGroup"})
    private Muscle muscle;
}
