package com.workoutlogger.workout_logger.service;

import com.workoutlogger.workout_logger.dto.ExerciseProgressDto;
import com.workoutlogger.workout_logger.model.*;
import com.workoutlogger.workout_logger.repository.ExerciseRepository;
import com.workoutlogger.workout_logger.repository.WorkoutExerciseRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ExerciseServiceTest {

    @Mock
    private ExerciseRepository exerciseRepository;

    @Mock
    private WorkoutExerciseRepository workoutExerciseRepository;

    @InjectMocks
    private ExerciseService exerciseService;

    @Test
    void getProgressForExercise_returnsCorrectMaxWeightAndVolume() {
        Workout workout1 = new Workout(LocalDate.of(2026, 8, 4), "Push Day", null);
        ReflectionTestUtils.setField(workout1, "id", 5L);

        Exercise exercise1 = new Exercise("Bench Press", "Chest", "Barbell");
        ReflectionTestUtils.setField(exercise1, "id", 5L);

        WorkoutExercise workoutExercise1 = new WorkoutExercise(workout1, exercise1, 1);
        ReflectionTestUtils.setField(workoutExercise1, "id", 6L);

        SetEntry set1 = new SetEntry(workoutExercise1, 1, 8, new BigDecimal("185.00"));
        SetEntry set2 = new SetEntry(workoutExercise1, 2, 10, new BigDecimal("205.00"));
        workoutExercise1.addSetEntry(set1);
        workoutExercise1.addSetEntry(set2);

        when(workoutExerciseRepository.findByExerciseIdOrderByWorkoutDate(5L)).thenReturn(List.of(workoutExercise1));

        List<ExerciseProgressDto> result = exerciseService.getProgressForExercise(5L);

        assertEquals(1, result.size()); //confirms one progress entry came back
        ExerciseProgressDto dto = result.getFirst();
        assertEquals(5L, dto.getWorkoutId()); //confirms the DTO correctly pulled workout's id (5L) off parent workout
        assertEquals(LocalDate.of(2026, 8, 4), dto.getDate()); //confirms DTO pulled workout's date correctly
        assertEquals(0, new BigDecimal("205.00").compareTo(dto.getMaxWeight())); //confirms heaviest set was correctly identified
        assertEquals(10, dto.getRepsAtMaxWeight()); //confirms reps recorded were pulled from correct (heaviest) set
        assertEquals(0, new BigDecimal("3530.00").compareTo(dto.getTotalVolume())); //confirms total volume. (185 x 8) + (205 x 10) = 3530

    }
}