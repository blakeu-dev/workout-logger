package com.workoutlogger.workout_logger.service;

import com.workoutlogger.workout_logger.model.Exercise;
import com.workoutlogger.workout_logger.model.SetEntry;
import com.workoutlogger.workout_logger.model.Workout;
import com.workoutlogger.workout_logger.model.WorkoutExercise;
import com.workoutlogger.workout_logger.repository.SetEntryRepository;
import com.workoutlogger.workout_logger.repository.WorkoutExerciseRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SetEntryServiceTest {

    @Mock
    private SetEntryRepository setEntryRepository;

    @Mock
    private WorkoutExerciseRepository workoutExerciseRepository;

    @InjectMocks
    private SetEntryService setEntryService;

    @Test
    void addSetToWorkoutExercise() {
        Exercise exercise1 = new Exercise("Bench Press", "Chest", "Barbell");
        ReflectionTestUtils.setField(exercise1,"id", 6L);

        Workout workout1 = new Workout(LocalDate.of(2026, 8, 4), "Push Day", null);
        ReflectionTestUtils.setField(workout1, "id", 5L);

        WorkoutExercise workoutExercise1 = new WorkoutExercise(workout1, exercise1, 1);
        ReflectionTestUtils.setField(workoutExercise1, "id", 7L);

        SetEntry setEntry1 = new SetEntry(workoutExercise1, 3, 8, new BigDecimal("135.00"));

        when(workoutExerciseRepository.findById(7L)).thenReturn(Optional.of(workoutExercise1));
        when(setEntryRepository.existsByWorkoutExerciseIdAndSetNumber(7L, 3)).thenReturn(false);
        when(setEntryRepository.save(any(SetEntry.class))).thenReturn(setEntry1);

        SetEntry result = setEntryService.addSetToWorkoutExercise(7L, 3, 8, new BigDecimal("135.00"));

        assertEquals(setEntry1, result);

    }

    @Test
    void addSetToWorkoutExerciseThrowsWhenSetNumberIsTaken() {
        Workout workout1 = new Workout(LocalDate.of(2026, 8, 4), "Push Day", null);
        ReflectionTestUtils.setField(workout1,"id", 5L);

        Exercise exercise1 = new Exercise("Bench Press", "Chest", "Barbell");
        ReflectionTestUtils.setField(exercise1, "id", 6L);

        WorkoutExercise workoutExercise1 = new WorkoutExercise(workout1, exercise1, 1);
        ReflectionTestUtils.setField(workoutExercise1, "id", 7L);

        when(workoutExerciseRepository.findById(7L)).thenReturn(Optional.of(workoutExercise1));
        when(setEntryRepository.existsByWorkoutExerciseIdAndSetNumber(7L, 3)).thenReturn(true);

        assertThrows(IllegalArgumentException.class, () -> setEntryService.addSetToWorkoutExercise(7L, 3, 8, new BigDecimal("135.00")));
    }
}