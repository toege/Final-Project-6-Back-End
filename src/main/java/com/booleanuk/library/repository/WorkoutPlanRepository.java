package com.booleanuk.library.repository;

import com.booleanuk.library.models.Muscle;
import com.booleanuk.library.models.WorkoutPlan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkoutPlanRepository extends JpaRepository<WorkoutPlan, Integer> {
}
