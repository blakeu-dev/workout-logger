package com.workoutlogger.workout_logger.controller;

import com.workoutlogger.workout_logger.model.WorkoutExercise;
import com.workoutlogger.workout_logger.service.WorkoutExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller that handles HTTP requests related to a workout exercise
 * Provides endpoints for reading, updating, and deleting a workout exercise
 */
@RestController
@RequestMapping("/api/workouts/{workoutId}/exercises")
public class WorkoutExerciseController {

    private final WorkoutExerciseService workoutExerciseService;

    /**
     * Creates a WorkoutExerciseController with specified workout exercise service
     * @param workoutExerciseService the service responsible for workout exercise related operations
     */
    @Autowired
    public WorkoutExerciseController(WorkoutExerciseService workoutExerciseService) {
        this.workoutExerciseService = workoutExerciseService;
    }

    /**
     * Retrieves list of exercises for a specific workout
     * @param workoutId the ID for the specified workout
     * @return the list of exercises in a specified workout
     */
    @GetMapping
    public List<WorkoutExercise> getExercisesForWorkout(@PathVariable Long workoutId) {
        return workoutExerciseService.getExerciseForWorkout(workoutId);
    }

    /**
     * Creates a WorkoutExercise association
     * @param workoutId the ID of the workout being added to
     * @param exerciseId the ID of the exercise being added
     * @param orderIndex the position in which the exercise is being added to
     * @return the newly created workout exercise association
     */
    @PostMapping
    public WorkoutExercise addExerciseToWorkout(@PathVariable Long workoutId, @RequestParam Long exerciseId, @RequestParam Integer orderIndex) {
        return workoutExerciseService.addExerciseToWorkout(workoutId, exerciseId, orderIndex);
    }

    /**
     * Deletes an exercise from a workout
     * @param workoutId the ID for a specified workout
     * @param workoutExerciseId the ID for the exercise being deleted in a workout
     */
    @DeleteMapping("/{workoutExerciseId}")
     public void removeExerciseFromWorkout(@PathVariable Long workoutId, @PathVariable Long workoutExerciseId) {
        workoutExerciseService.removeExerciseFromWorkout(workoutId,workoutExerciseId);
    }


}
