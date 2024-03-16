package com.booleanuk.library.repository;
import com.booleanuk.library.models.Muscle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MuscleRepository extends JpaRepository<Muscle, Integer> {
}