package com.workoutlogger.workout_logger.controller;

import com.workoutlogger.workout_logger.dto.ExerciseProgressDto;
import com.workoutlogger.workout_logger.model.Exercise;
import com.workoutlogger.workout_logger.service.ExerciseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller that handles HTTP requests for exercises
 * Provides endpoints for creating, reading, updating, and deleting exercises
 */
@RestController
@RequestMapping("/api/exercises")
public class ExerciseController {

    private final ExerciseService exerciseService;

    /**
     * Creates an ExerciseController with the specific exercise service
     * @param exerciseService the service responsible for exercise related operations
     */
    @Autowired
    public ExerciseController(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    /**
     * Retrieves all exercises
     * @param pageable pagination information submitted by client
     * @return a page containing exercises
     */
    @GetMapping
    public Page<Exercise> getAllExercises(Pageable pageable) {
        return exerciseService.getAllExercises(pageable);
    }

    /**
     * Retrieves exercise with the specified ID
     * @param id the identifier for exercise
     * @return the exercise with specified ID
     */
    @GetMapping("/{id}")
    public Exercise getExerciseById(@PathVariable Long id) {
        return exerciseService.getExerciseById(id);
    }

    /**
     * Creates an exercise using information submitted by client through request body
     * @param exercise the exercise being created
     * @return the newly created exercise
     */
    @PostMapping
    public Exercise createExercise(@Valid @RequestBody Exercise exercise) {
        return exerciseService.createExercise(exercise);
    }

    /**
     * Updates the exercise with information submitted by client
     * @param id the ID of the exercise being updated
     * @param exercise the updated exercise
     * @return the updated exercise
     */
    @PutMapping("/{id}")
    public Exercise updateExercise(@PathVariable Long id, @Valid @RequestBody Exercise exercise) {
        return exerciseService.updateExercise(id, exercise);
    }

    /**
     * Deletes the exercise with the specified ID
     * @param id the identifier used to delete exercise
     */
    @DeleteMapping("/{id}")
    public void deleteExercise(@PathVariable Long id) {
        exerciseService.deleteExercise(id);
    }

    /**
     * Retrieves progress information for specified exercise
     * @param id the identifier used to find the exercise
     * @return the progress for the exercise
     */
    @GetMapping("/{id}/progress")
    public List<ExerciseProgressDto> getProgress (@PathVariable Long id) {
        return exerciseService.getProgressForExercise(id);
    }

    /**
     * Retrieves the exercise list associated with name
     * @param name the name of the exercise
     * @return the exercise list associated with specified name
     */
    @GetMapping("/search")
    public List<Exercise> searchByName(@RequestParam String name) {
        return exerciseService.searchByName(name);
    }

    /**
     * Retrieves exercise list associated with category
     * @param category the category of the exercise
     * @return the exercise list associated with the specified category
     */
    @GetMapping("/filter")
    public List<Exercise> searchByCategory(@RequestParam String category) {
        return exerciseService.searchByCategory(category);
    }
}
