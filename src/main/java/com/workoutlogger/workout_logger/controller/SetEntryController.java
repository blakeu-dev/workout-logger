package com.workoutlogger.workout_logger.controller;

import com.workoutlogger.workout_logger.model.SetEntry;
import com.workoutlogger.workout_logger.service.SetEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * REST controller that handles HTTP requests for set entries
 * Provides endpoints for reading, updating, and deleting set entries
 */
@RestController
@RequestMapping("/api/workout-exercises/{workoutExerciseId}/sets")
public class SetEntryController {

    private final SetEntryService setEntryService;

    /**
     * Creates a SetEntry with the specified set entry service needed
     * @param setEntryService the specific service for SetEntry related operations
     */
    @Autowired
    public SetEntryController(SetEntryService setEntryService) {
        this.setEntryService = setEntryService;
    }

    /**
     * Retrieves the sets associated with the specified workout exercise
     * @param workoutExerciseId the identifier for a workout exercise
     * @return the sets associated with a specific workout exercise
     */
    @GetMapping
    public List<SetEntry> getSetsForExercise(@PathVariable Long workoutExerciseId) {
        return setEntryService.getSetsForWorkoutExercise(workoutExerciseId);
    }

    /**
     * Adds set number, reps, and weight to a workout exercise
     * @param workoutExerciseId the ID used to find a workout exercise
     * @param setNumber the set number that will be added to a workout exercise
     * @param reps the number of repetitions that will be added to a workout exercise
     * @param weight the weight that will be added to a workout exercise
     * @return the updated set entry
     */
    @PostMapping
    public SetEntry addSetToWorkoutExercise(@PathVariable Long workoutExerciseId, @RequestParam Integer setNumber, @RequestParam Integer reps, @RequestParam BigDecimal weight) {
        return setEntryService.addSetToWorkoutExercise(workoutExerciseId, setNumber, reps, weight);
    }

    /**
     * Deletes set entry for specific workout exercise
     * @param workoutExerciseId the ID used to find specified workout exercise
     * @param id ID of the set entry
     */
    @DeleteMapping("/{id}")
    public void removeSetEntry(@PathVariable Long workoutExerciseId, @PathVariable Long id) {
        setEntryService.removeSet(workoutExerciseId, id);
    }

}
