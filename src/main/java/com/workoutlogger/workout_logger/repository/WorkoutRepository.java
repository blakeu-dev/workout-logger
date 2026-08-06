package com.workoutlogger.workout_logger.repository;

import com.workoutlogger.workout_logger.model.Workout;
import com.workoutlogger.workout_logger.model.WorkoutExercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface WorkoutRepository extends JpaRepository<Workout, Long> {

    List<Workout> findByDateBetweenOrderByDate(LocalDate startDate, LocalDate endDate);

}
