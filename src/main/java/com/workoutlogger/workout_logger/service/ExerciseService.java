package com.workoutlogger.workout_logger.service;

import com.workoutlogger.workout_logger.exception.ResourceNotFoundException;
import com.workoutlogger.workout_logger.model.Exercise;
import com.workoutlogger.workout_logger.repository.ExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExerciseService {

    private final ExerciseRepository exerciseRepository;

    @Autowired
    public ExerciseService(ExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
    }

    public List<Exercise> getAllExercises() {
        return exerciseRepository.findAll();
    }

    public Exercise getExerciseById(Long id) {
        return exerciseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Exercise not found with id: " + id));
    }

    public Exercise createExercise(Exercise exercise) {
        if (exercise.getName() == null || exercise.getName().isBlank()) {
            throw new IllegalArgumentException("Exercise name is required.");
        }
        return exerciseRepository.save(exercise);
    }

    public Exercise updateExercise(Long id, Exercise updatedExercise) {
        Exercise existing = getExerciseById(id);
        existing.setName(updatedExercise.getName());
        existing.setCategory(updatedExercise.getCategory());
        existing.setEquipment(updatedExercise.getEquipment());
        return exerciseRepository.save(existing);
    }

    public void deleteExercise(Long id) {
        Exercise existing = getExerciseById(id);
        exerciseRepository.delete(existing);
    }
}
