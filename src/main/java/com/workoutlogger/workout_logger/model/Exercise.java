package com.workoutlogger.workout_logger.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

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

    //No args constructor for JPA
    public Exercise() {}

    public Exercise(String name, String category, String equipment) {
        this.name = name;
        this.category = category;
        this.equipment = equipment;
    }

    //Getter for id
    public Long getId() {
        return id;
    }

    //Getter for name
    public String getName() {
        return name;
    }
    //Setter for name
    public void setName(String name) {
        this.name = name;
    }

    //Getter for category
    public String getCategory() {
        return category;
    }

    //Setter for category
    public void setCategory(String category) {
        this.category = category;
    }

    //Getter for equipment
    public String getEquipment() {
        return equipment;
    }

    //setter fpr equipment
    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }
}
