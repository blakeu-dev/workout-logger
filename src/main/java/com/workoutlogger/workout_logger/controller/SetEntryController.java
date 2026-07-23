package com.workoutlogger.workout_logger.controller;

import com.workoutlogger.workout_logger.model.SetEntry;
import com.workoutlogger.workout_logger.service.SetEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/workout-exercises/{workoutExerciseId}/sets")
public class SetEntryController {

    private final SetEntryService setEntryService;
    
    @Autowired
    public SetEntryController(SetEntryService setEntryService) {
        this.setEntryService = setEntryService;
    }
    
    @GetMapping
    public List<SetEntry> getSetsForExercise(@PathVariable Long workoutExerciseId) {
        return setEntryService.getSetsForWorkoutExercise(workoutExerciseId);
    }
    
    @PostMapping
    public SetEntry addSetToWorkoutExercise(@PathVariable Long workoutExerciseId, @RequestParam Integer setNumber, @RequestParam Integer reps, @RequestParam BigDecimal weight) {
        return setEntryService.addSetToWorkoutExercise(workoutExerciseId, setNumber, reps, weight);
    }

    @DeleteMapping("/{id}")
    public void removeSetEntry(@PathVariable Long workoutExerciseId, @PathVariable Long id) {
        setEntryService.removeSet(id);
    }

}
