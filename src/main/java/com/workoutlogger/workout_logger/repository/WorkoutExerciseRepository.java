package com.workoutlogger.workout_logger.repository;

import com.workoutlogger.workout_logger.model.WorkoutExercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkoutExerciseRepository extends JpaRepository<WorkoutExercise, Long> {

    List<WorkoutExercise> findByWorkoutIdOrderByOrderIndex(Long workoutId);

    boolean existsByWorkoutIdAndOrderIndex(Long workoutId, Integer orderIndex);

    List<WorkoutExercise> findByExerciseIdOrderByWorkoutDate(Long exerciseId);
}
