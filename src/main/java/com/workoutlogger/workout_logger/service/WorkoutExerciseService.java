package com.workoutlogger.workout_logger.service;

import com.workoutlogger.workout_logger.exception.ResourceNotFoundException;
import com.workoutlogger.workout_logger.model.Exercise;
import com.workoutlogger.workout_logger.model.Workout;
import com.workoutlogger.workout_logger.model.WorkoutExercise;
import com.workoutlogger.workout_logger.repository.ExerciseRepository;
import com.workoutlogger.workout_logger.repository.WorkoutExerciseRepository;
import com.workoutlogger.workout_logger.repository.WorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkoutExerciseService {

    private final WorkoutExerciseRepository workoutExerciseRepository;
    private final WorkoutRepository workoutRepository;
    private final ExerciseRepository exerciseRepository;

    @Autowired 
    public WorkoutExerciseService(WorkoutExerciseRepository workoutExerciseRepository, WorkoutRepository workoutRepository, ExerciseRepository exerciseRepository) {
        this.workoutExerciseRepository = workoutExerciseRepository;
        this.workoutRepository = workoutRepository;
        this.exerciseRepository = exerciseRepository;
    }

    public List<WorkoutExercise> getExerciseForWorkout(Long workoutId) {
        return workoutExerciseRepository.findByWorkoutIdOrderByOrderIndex(workoutId);
    }

    public WorkoutExercise addExerciseToWorkout(Long workoutId, Long exerciseId, Integer orderIndex) {
        Workout workout = workoutRepository.findById(workoutId)
                .orElseThrow(() -> new ResourceNotFoundException("Workout not found with id: " + workoutId));

        Exercise exercise = exerciseRepository.findById(exerciseId)
                .orElseThrow(() -> new ResourceNotFoundException("Exercise not found with id: " + exerciseId));

        WorkoutExercise workoutExercise = new WorkoutExercise(workout, exercise, orderIndex);
        return workoutExerciseRepository.save(workoutExercise);

    }


    public void removeExerciseFromWorkout(Long id) {
        WorkoutExercise existing = workoutExerciseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("WorkoutExercise not found with id: " + id));
        workoutExerciseRepository.delete(existing);

    }
}
