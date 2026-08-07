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

@RestController
@RequestMapping("/api/exercises")
public class ExerciseController {

    private final ExerciseService exerciseService;

    @Autowired
    public ExerciseController(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    @GetMapping
    public Page<Exercise> getAllExercises(Pageable pageable) {
        return exerciseService.getAllExercises(pageable);
    }

    @GetMapping("/{id}")
    public Exercise getExerciseById(@PathVariable Long id) {
        return exerciseService.getExerciseById(id);
    }

    @PostMapping
    public Exercise createExercise(@Valid @RequestBody Exercise exercise) {
        return exerciseService.createExercise(exercise);
    }

    @PutMapping("/{id}")
    public Exercise updateExercise(@PathVariable Long id, @Valid @RequestBody Exercise exercise) {
        return exerciseService.updateExercise(id, exercise);
    }

    @DeleteMapping("/{id}")
    public void deleteExercise(@PathVariable Long id) {
        exerciseService.deleteExercise(id);
    }

    @GetMapping("/{id}/progress")
    public List<ExerciseProgressDto> getProgress (@PathVariable Long id) {
        return exerciseService.getProgressForExercise(id);
    }

    @GetMapping("/search")
    public List<Exercise> searchByName(@RequestParam String name) {
        return exerciseService.searchByName(name);
    }

    @GetMapping("/filter")
    public List<Exercise> searchByCategory(@RequestParam String category) {
        return exerciseService.searchByCategory(category);
    }
}
