package com.workoutlogger.workout_logger.controller;

import com.workoutlogger.workout_logger.model.Workout;
import com.workoutlogger.workout_logger.service.WorkoutService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * REST controller that handles HTTP requests for workouts
 * Provides endpoints for creating, retrieving, updating, and deleting workouts.
 */
@RestController
@RequestMapping("/api/workouts")
public class WorkoutController {

    private final WorkoutService workoutService;

    /**
     * Creates a WorkoutController with the specified workout service
     * @param workoutService the service responsible for workout related operations
     */
    @Autowired
    public WorkoutController(WorkoutService workoutService) {
        this.workoutService = workoutService;
    }

    /**
     * Retrieves all workouts
     * @param pageable pagination information provided by client
     * @return a page containing the workouts
     */
    @GetMapping
    public Page<Workout> getAllWorkouts(Pageable pageable) {
        return workoutService.getAllWorkouts(pageable);
    }

    /**
     * Retrieves a workout by its id
     * @param id the id of the workout to retrieve
     * @return the workout with specified id
     */
    @GetMapping("/{id}")
    public Workout getWorkoutById(@PathVariable Long id) {
        return workoutService.getWorkoutById(id);
    }

    /**
     * Creates a new workout using the information in the request body
     * @param workout the workout data from client
     * @return the created workout
     */
    @PostMapping
    public Workout createWorkout(@Valid @RequestBody Workout workout) {
        return workoutService.createWorkout(workout);
    }

    /**
     * Updates an existing workout with specified id
     * @param id the id of the workout to update
     * @param workout the updated workout data submitted by client
     * @return the updated workout
     */
    @PutMapping("/{id}")
    public Workout updateWorkout(@PathVariable Long id, @Valid @RequestBody Workout workout) {
        return workoutService.updateWorkout(id, workout);
    }

    /**
     * Deletes the workout with specified id
     * @param id the id of the workout to delete
     */
    @DeleteMapping("/{id}")
    public void deleteWorkout(@PathVariable Long id) {
        workoutService.deleteWorkout(id);
    }

    /**
     * Retrieves list of workouts that occurred within a specified date range
     * @param startDate the beginning of date range
     * @param endDate the end of date range
     * @return a list of workouts within the date range
     */
    @GetMapping("/search")
    public List<Workout> getWorkoutsByDateRange(@RequestParam LocalDate startDate, @RequestParam LocalDate endDate) {
        return workoutService.getWorkoutsByDateRange(startDate, endDate);
    }
}
