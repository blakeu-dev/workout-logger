package com.workoutlogger.workout_logger.service;

import com.workoutlogger.workout_logger.model.Exercise;
import com.workoutlogger.workout_logger.model.Workout;
import com.workoutlogger.workout_logger.model.WorkoutExercise;
import com.workoutlogger.workout_logger.repository.ExerciseRepository;
import com.workoutlogger.workout_logger.repository.WorkoutExerciseRepository;
import com.workoutlogger.workout_logger.repository.WorkoutRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class WorkoutExerciseServiceTest {

    @Mock
    private ExerciseRepository exerciseRepository;

    @Mock
    private WorkoutExerciseRepository workoutExerciseRepository;

    @Mock
    private WorkoutRepository workoutRepository;

    @InjectMocks
    private WorkoutExerciseService workoutExerciseService;

    @Test
    void addExerciseToWorkout() {
        Exercise exercise1 = new Exercise("Bench Press", "Chest", "Barbell");
        ReflectionTestUtils.setField(exercise1,"id", 6L);

        Workout workout1 = new Workout(LocalDate.of(2026, 8, 4), "Push Day", null);
        ReflectionTestUtils.setField(workout1, "id", 5L);

        WorkoutExercise workoutExercise1 = new WorkoutExercise(workout1, exercise1, 1);
        ReflectionTestUtils.setField(workoutExercise1, "id", 7L);

        when(workoutRepository.findById(5L)).thenReturn(Optional.of(workout1));
        when(exerciseRepository.findById(6L)).thenReturn(Optional.of(exercise1));
        when(workoutExerciseRepository.existsByWorkoutIdAndOrderIndex(5L, 1)).thenReturn(false);
        when(workoutExerciseRepository.save(any(WorkoutExercise.class))).thenReturn(workoutExercise1);

        WorkoutExercise result = workoutExerciseService.addExerciseToWorkout(5L, 6L, 1);
        assertEquals(workoutExercise1, result);

    }

    @Test
    void addExerciseToWorkoutThrowsWhenOrderIndexIsTaken() {
        Workout workout1 = new Workout(LocalDate.of(2026, 8, 4), "Push Day", null);
        ReflectionTestUtils.setField(workout1, "id", 5L);

        Exercise exercise1 = new Exercise("Bench Press", "Chest", "Barbell");
        ReflectionTestUtils.setField(exercise1, "id", 6L);

        when(workoutRepository.findById(5L)).thenReturn(Optional.of(workout1));
        when(exerciseRepository.findById(6L)).thenReturn(Optional.of(exercise1));
        when(workoutExerciseRepository.existsByWorkoutIdAndOrderIndex(5L, 1)).thenReturn(true);

        assertThrows(IllegalArgumentException.class, () -> workoutExerciseService.addExerciseToWorkout(5L,6L, 1));

    }
}
