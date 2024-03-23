package com.booleanuk.library.repository;

import com.booleanuk.library.models.Muscle;
import com.booleanuk.library.models.User;
import com.booleanuk.library.models.WorkoutPlan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkoutPlanRepository extends JpaRepository<WorkoutPlan, Integer> {
    List<WorkoutPlan> findByUser(User user);

}
