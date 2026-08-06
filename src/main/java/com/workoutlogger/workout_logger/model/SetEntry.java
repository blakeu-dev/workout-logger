package com.workoutlogger.workout_logger.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.math.BigDecimal;

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

    //No args constructor for JPA
    public SetEntry () {}

    public SetEntry (WorkoutExercise workoutExercise, Integer setNumber, Integer reps, BigDecimal weight) {
        this.workoutExercise = workoutExercise;
        this.setNumber = setNumber;
        this.reps = reps;
        this.weight = weight;
    }

    // Getter for id
    public Long getId() {
        return id;
    }

    // Getter for workoutExercise
    public WorkoutExercise getWorkoutExercise() {
        return workoutExercise;
    }

    // Setter for workoutExercise
    public void setWorkoutExercise(WorkoutExercise workoutExercise) {
        this.workoutExercise = workoutExercise;
    }

    // Getter for setNumber
    public Integer getSetNumber() {
        return setNumber;
    }

    // Setter for setNumber
    public void setSetNumber(Integer setNumber) {
        this.setNumber = setNumber;
    }

    // Getter for reps
    public Integer getReps() {
        return reps;
    }

    // Setter for reps
    public void setReps(Integer reps) {
        this.reps = reps;
    }

    // Getter for weight
    public BigDecimal getWeight() {
        return weight;
    }

    // Setter for weight
    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }
}
