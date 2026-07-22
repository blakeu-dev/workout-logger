package com.workoutlogger.workout_logger.repository;

import com.workoutlogger.workout_logger.model.WorkoutExercise;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkoutExerciseRepository {

    List<WorkoutExercise> findByWorkoutIdOrderByOrderIndex(Long workoutId);
}
