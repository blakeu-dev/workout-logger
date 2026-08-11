package com.workoutlogger.workout_logger.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a singular workout
 * Each workout will have a date, name, and optionally notes
 */
@Entity
@Table(name = "workouts")
public class Workout {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotNull(message = "Workout date is required")
    private LocalDate date;

    @NotBlank(message = "Workout name is required.")
    private String name;

    @Column(columnDefinition = "TEXT")
    private String notes;

    @OneToMany(mappedBy = "workout", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<WorkoutExercise> workoutExercises = new ArrayList<>();

    /**
     * No args constructor for JPA
     */
    public Workout() {}

    /**
     * Creates a workout with date, name, and notes
     * @param date the date associated with the workout
     * @param name the name of the workout
     * @param notes the notes for the workout
     */
    public Workout(LocalDate date, String name, String notes) {
            this.date = date;
            this.name = name;
            this.notes = notes;
    }

    /**
     * Returns the identifier of the workout
     * @return returns the workout ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Returns the specific date of the workout
     * @return returns the workout's date
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Will set the date for the workout
     * @param date the date of a specific workout
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * Returns the name of the workout
     * @return returns the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of a workout
     * @param name sets the name of workout
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the notes of the given workout
     * @return returns the notes of workout
     */
    public String getNotes() {
        return notes;
    }

    /**
     * Sets the notes of the workout
     * @param notes notes that will be set for a workout
     */
    public void setNotes(String notes) {
        this.notes = notes;
    }

    /**
     * Returns the list of workout exercises
     * @return returns the list of workout exercises
     */
    public List<WorkoutExercise> getWorkoutExercises() {
        return workoutExercises;
    }
}
