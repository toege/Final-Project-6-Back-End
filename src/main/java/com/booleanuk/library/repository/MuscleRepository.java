package com.booleanuk.library.repository;
import com.booleanuk.library.models.Muscle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MuscleRepository extends JpaRepository<Muscle, Integer> {
    @Query("SELECT m FROM Muscle m LEFT JOIN FETCH m.workouts")
    List<Muscle> findAllWithWorkouts();


}