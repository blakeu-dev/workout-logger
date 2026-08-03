package com.workoutlogger.workout_logger.service;

import java.math.BigDecimal;

import com.workoutlogger.workout_logger.exception.ResourceNotFoundException;
import com.workoutlogger.workout_logger.model.SetEntry;
import com.workoutlogger.workout_logger.model.WorkoutExercise;
import com.workoutlogger.workout_logger.repository.SetEntryRepository;
import com.workoutlogger.workout_logger.repository.WorkoutExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SetEntryService {

    private final WorkoutExerciseRepository workoutExerciseRepository;
    private final SetEntryRepository setEntryRepository;

    @Autowired
    public SetEntryService(WorkoutExerciseRepository workoutExerciseRepository, SetEntryRepository setEntryRepository) {
        this.workoutExerciseRepository = workoutExerciseRepository;
        this.setEntryRepository = setEntryRepository;
    }

    public List<SetEntry> getSetsForWorkoutExercise(Long workoutExerciseId) {
        return setEntryRepository.findByWorkoutExerciseIdOrderBySetNumber(workoutExerciseId);
    }

    public SetEntry addSetToWorkoutExercise(Long workoutExerciseId, Integer setNumber, Integer reps, BigDecimal weight) {
        WorkoutExercise workoutExercise = workoutExerciseRepository.findById(workoutExerciseId)
                .orElseThrow(() -> new ResourceNotFoundException("WorkoutExercise not found with id: " + workoutExerciseId));

        SetEntry setEntry = new SetEntry(workoutExercise, setNumber, reps, weight);
        return setEntryRepository.save(setEntry);

    }

    public void removeSet(Long id) {
        SetEntry existing = setEntryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("WorkoutExercise not found with id: " + id));
        setEntryRepository.delete(existing);
    }

}
