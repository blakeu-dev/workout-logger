package com.workoutlogger.workout_logger.service;

import com.workoutlogger.workout_logger.repository.ExerciseRepository;
import com.workoutlogger.workout_logger.repository.WorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WorkoutExerciseService {

    private final WorkoutRepository workoutRepository;
    private final ExerciseRepository exerciseRepository;

    @Autowired
    public WorkoutExerciseService(WorkoutRepository workoutRepository, ExerciseRepository exerciseRepository) {
        this.workoutRepository = workoutRepository;
        this.exerciseRepository = exerciseRepository;
    }

}
