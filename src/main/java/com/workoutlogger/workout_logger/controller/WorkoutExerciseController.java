package com.workoutlogger.workout_logger.controller;

import com.workoutlogger.workout_logger.model.WorkoutExercise;
import com.workoutlogger.workout_logger.service.WorkoutExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/workouts/{workoutId}/exercises")
public class WorkoutExerciseController {

    private final WorkoutExerciseService workoutExerciseService;

    @Autowired
    public WorkoutExerciseController(WorkoutExerciseService workoutExerciseService) {
        this.workoutExerciseService = workoutExerciseService;
    }

    @GetMapping
    public List<WorkoutExercise> getExercisesForWorkout(@PathVariable Long workoutId) {
        return workoutExerciseService.getExerciseForWorkout(workoutId);
    }

    @PostMapping
    public WorkoutExercise addExerciseToWorkout(@PathVariable Long workoutId, @RequestParam Long exerciseId, @RequestParam Integer orderIndex) {
        return workoutExerciseService.addExerciseToWorkout(workoutId, exerciseId, orderIndex);
    }

    @DeleteMapping("/{id}")
     public void removeExerciseFromWorkout(@PathVariable Long workoutId, @PathVariable Long id) {
        workoutExerciseService.removeExerciseFromWorkout(id);
    }


}
