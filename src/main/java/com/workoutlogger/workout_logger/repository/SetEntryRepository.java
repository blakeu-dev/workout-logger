package com.workoutlogger.workout_logger.repository;

import com.workoutlogger.workout_logger.model.SetEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SetEntryRepository extends JpaRepository<SetEntry, Long> {

    List<SetEntry> findByWorkoutExerciseIdOrderBySetNumber(Long workoutExerciseId);

    boolean existsByWorkoutExerciseIdAndSetNumber(Long workoutExerciseId, Integer setNumber);
}