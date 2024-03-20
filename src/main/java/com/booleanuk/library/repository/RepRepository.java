package com.booleanuk.library.repository;

import com.booleanuk.library.models.Rep;
import com.booleanuk.library.models.WorkoutPlan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepRepository extends JpaRepository<Rep, Integer> {
}
