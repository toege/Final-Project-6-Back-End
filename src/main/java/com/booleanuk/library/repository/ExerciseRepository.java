package com.booleanuk.library.repository;

import com.booleanuk.library.models.Exercise;
import com.booleanuk.library.models.Muscle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExerciseRepository extends JpaRepository<Exercise, Integer> {
}
