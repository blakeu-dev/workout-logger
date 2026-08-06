package com.workoutlogger.workout_logger.service;

import com.workoutlogger.workout_logger.dto.ExerciseProgressDto;
import com.workoutlogger.workout_logger.exception.ResourceNotFoundException;
import com.workoutlogger.workout_logger.model.Exercise;
import com.workoutlogger.workout_logger.model.SetEntry;
import com.workoutlogger.workout_logger.model.WorkoutExercise;
import com.workoutlogger.workout_logger.repository.ExerciseRepository;
import com.workoutlogger.workout_logger.repository.WorkoutExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExerciseService {

    private final ExerciseRepository exerciseRepository;
    private final WorkoutExerciseRepository workoutExerciseRepository;

    @Autowired
    public ExerciseService(ExerciseRepository exerciseRepository, WorkoutExerciseRepository workoutExerciseRepository) {
        this.exerciseRepository = exerciseRepository;
        this.workoutExerciseRepository = workoutExerciseRepository;
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

    public List<ExerciseProgressDto> getProgressForExercise(Long exerciseID) {
        List<WorkoutExercise> workoutExercises = workoutExerciseRepository.findByExerciseIdOrderByWorkoutDate(exerciseID);

        List<ExerciseProgressDto> progress = new ArrayList<>();

        for (WorkoutExercise workoutExercise : workoutExercises) {

            if (workoutExercise.getSetEntries().isEmpty()) {
                continue;
            }

            BigDecimal maxWeight = BigDecimal.ZERO;
            int repsAtMaxWeight = 0;
            BigDecimal totalVolume = BigDecimal.ZERO;

            for (SetEntry set : workoutExercise.getSetEntries()) {
                if (set.getWeight() != null && set.getWeight().compareTo(maxWeight) > 0) {
                    maxWeight = set.getWeight();
                    repsAtMaxWeight = set.getReps();
                }
                if (set.getWeight() != null) {
                    totalVolume = totalVolume.add(set.getWeight().multiply(BigDecimal.valueOf(set.getReps())));
                }
            }

            progress.add(new ExerciseProgressDto(
                    workoutExercise.getWorkout().getId(), workoutExercise.getWorkout().getDate(),
                    maxWeight,
                    repsAtMaxWeight,
                    totalVolume
            ));
        }
        return progress;
    }

    public List<Exercise> searchByName(String name) {
        return exerciseRepository.findByNameContainingIgnoreCase(name);
    }

    public List<Exercise> searchByCategory(String category) {
        return exerciseRepository.findByCategoryContainingIgnoreCase(category);
    }
}
