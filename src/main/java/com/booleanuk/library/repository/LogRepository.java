package com.booleanuk.library.repository;

import com.booleanuk.library.models.Log;
import com.booleanuk.library.models.Muscle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LogRepository extends JpaRepository<Log, Integer> {
    List<Log> findByUserId(int userId);
}
