package com.workoutlogger.workout_logger.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.math.BigDecimal;

/**
 * Represents an individual set performed for an exercise in a workout
 * This gives each exercise a specific number of sets, reps, and weight.
 */
@Entity
@Table(name = "set_entries")
public class SetEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "workout_exercise_id", nullable = false)
    @JsonIgnore
    private WorkoutExercise workoutExercise;

    @Column(name = "set_number", nullable = false)
    private Integer setNumber;

    @Column(nullable = false)
    private Integer reps;

    @Column(precision = 6, scale = 2)
    private BigDecimal weight;

    /**
     * No args constructor for JPA
     */
    public SetEntry () {}

    /**
     * Constructor will initialize workoutExercise, set number, reps, and weight
     * @param workoutExercise the workoutExercise
     * @param setNumber the set number
     * @param reps the reps
     * @param weight the weight
     */
    public SetEntry (WorkoutExercise workoutExercise, Integer setNumber, Integer reps, BigDecimal weight) {
        this.workoutExercise = workoutExercise;
        this.setNumber = setNumber;
        this.reps = reps;
        this.weight = weight;
    }

    /**
     * Returns the id for setEntry
     * @return returns the id for setEntry
     */
    public Long getId() {
        return id;
    }

    /**
     * Returns the workoutExercise for setEntry
     * @return returns the workoutExercise for the setEntry
     */
    public WorkoutExercise getWorkoutExercise() {
        return workoutExercise;
    }

    /**
     * Sets the workoutExercise
     * @param workoutExercise the workoutExercise being set
     */
    public void setWorkoutExercise(WorkoutExercise workoutExercise) {
        this.workoutExercise = workoutExercise;
    }

    /**
     * Returns the set number for setEntry
     * @return returns the set number
     */
    public Integer getSetNumber() {
        return setNumber;
    }

    /**
     * Sets the set number for setEntry
     * @param setNumber the set number that will be set
     */
    public void setSetNumber(Integer setNumber) {
        this.setNumber = setNumber;
    }

    /**
     * Returns the reps for setEntry
     * @return returns the reps for setEntry
     */
    public Integer getReps() {
        return reps;
    }

    /**
     * Sets the number of reps
     * @param reps the reps that will be set
     */
    public void setReps(Integer reps) {
        this.reps = reps;
    }

    /**
     * Returns the weight for setEntry
     * @return return the weight
     */
    public BigDecimal getWeight() {
        return weight;
    }

    /**
     * Sets the weight for setEntry
     * @param weight weight that will be set
     */
    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }
}
