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

@RestController
@RequestMapping("/api/workouts")
public class WorkoutController {

    private final WorkoutService workoutService;

    @Autowired
    public WorkoutController(WorkoutService workoutService) {
        this.workoutService = workoutService;
    }

    @GetMapping
    public Page<Workout> getAllWorkouts(Pageable pageable) {
        return workoutService.getAllWorkouts(pageable);
    }

    @GetMapping("/{id}")
    public Workout getWorkoutById(@PathVariable Long id) {
        return workoutService.getWorkoutById(id);
    }

    @PostMapping
    public Workout createWorkout(@Valid @RequestBody Workout workout) {
        return workoutService.createWorkout(workout);
    }

    @PutMapping("/{id}")
    public Workout updateWorkout(@PathVariable Long id, @Valid @RequestBody Workout workout) {
        return workoutService.updateWorkout(id, workout);
    }

    @DeleteMapping("/{id}")
    public void deleteWorkout(@PathVariable Long id) {
        workoutService.deleteWorkout(id);
    }

    @GetMapping("/search")
    public List<Workout> getWorkoutsByDateRange(@RequestParam LocalDate startDate, @RequestParam LocalDate endDate) {
        return workoutService.getWorkoutsByDateRange(startDate, endDate);
    }
}
