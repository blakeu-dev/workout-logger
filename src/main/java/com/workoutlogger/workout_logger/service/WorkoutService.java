package com.workoutlogger.workout_logger.service;

import com.workoutlogger.workout_logger.exception.ResourceNotFoundException;
import com.workoutlogger.workout_logger.model.Workout;
import com.workoutlogger.workout_logger.repository.WorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Service
public class WorkoutService {

    private final WorkoutRepository workoutRepository;

    @Autowired
    public WorkoutService(WorkoutRepository workoutRepository) {
        this.workoutRepository = workoutRepository;
    }

    public Page<Workout> getAllWorkouts(Pageable pageable) {
        return workoutRepository.findAll(pageable);
    }

    public Workout getWorkoutById(Long id) {
        return workoutRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Workout not found with id: " + id));
    }

    public Workout createWorkout(Workout workout) {
        if (workout.getDate() == null) {
            throw new IllegalArgumentException("Workout date is required.");
        }
        return workoutRepository.save(workout);
    }

    public Workout updateWorkout(Long id, Workout updatedWorkout) {
        if (updatedWorkout.getDate() == null) {
            throw new IllegalArgumentException("Date is required.");
        }

        Workout existing = getWorkoutById(id);
        existing.setName(updatedWorkout.getName());
        existing.setDate(updatedWorkout.getDate());
        existing.setNotes(updatedWorkout.getNotes());
        return workoutRepository.save(existing);
    }

    public void deleteWorkout(Long id) {
        Workout existing = getWorkoutById(id);
        workoutRepository.delete(existing);
    }

    public List<Workout> getWorkoutsByDateRange(LocalDate startDate, LocalDate endDate) {
        return workoutRepository.findByDateBetweenOrderByDate(startDate, endDate);
    }


}
