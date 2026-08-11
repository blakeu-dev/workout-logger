package com.workoutlogger.workout_logger.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * Represents a single exercise definition.
 * Exercises are reused across many workouts through WorkoutExercise join.
 * Ex: ("Bench Press")
 */
@Entity
@Table(name = "exercises")
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //Exercise id

    @Column(nullable = false)
    @NotBlank(message = "Exercise name is required.")
    private String name;

    @NotBlank(message = "Category is required.")
    private String category;

    @NotBlank(message = "Equipment is required.")
    private String equipment;

    /**
     * No args constructor for JPA
     */
    public Exercise() {}

    /**
     * Constructor initializes name, category, and equipment for exercise class
     * @param name name of exercise
     * @param category category of exercise
     * @param equipment equipment used for exercise
     */
    public Exercise(String name, String category, String equipment) {
        this.name = name;
        this.category = category;
        this.equipment = equipment;
    }

    /**
     * Method will return id of exercise
     * @return returns the id of exercise
     */
    public Long getId() {
        return id;
    }

    /**
     * Method will return the name of the exercise
     * @return returns name of the exercise
     */
    public String getName() {
        return name;
    }

    /**
     * Method will set the name of the exercise
     * @param name name that will be used to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Method will get the category of the exercise
     * @return returns the category of the exercise
     */
    public String getCategory() {
        return category;
    }

    /**
     * Method will set the name of the exercise
     * @param category category of the exercise that will be set
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Method will get the equipment for the exercise
     * @return returns the equipment used for the exercise
     */
    public String getEquipment() {
        return equipment;
    }

    /**
     * Method will set the equipment for the exercise
     * @param equipment the equipment that will be set
     */
    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }
}
