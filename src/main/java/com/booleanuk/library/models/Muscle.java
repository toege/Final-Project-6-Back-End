package com.booleanuk.library.models;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name = "muscles")
public class Muscle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "muscleName")
    private String muscleName;

    @Column(name = "muscleGroup")
    private String muscleGroup;

    @OneToMany(mappedBy = "muscle", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIncludeProperties(value = {"id", "muscleName", "muscleGroup"})
    private Set<Workout> workouts = new HashSet<>();

    public Muscle(int id, String muscleName, String muscleGroup) {
        this.id = id;
        this.muscleName = muscleName;
        this.muscleGroup = muscleGroup;
    }
}
