package com.workoutlogger.workout_logger.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "workout_exercise")
public class WorkoutExercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "workout_id", nullable = false)
    private Workout workout;

    @ManyToOne
    @JoinColumn(name = "exercise_id", nullable = false)
    private Exercise exercise;

    @Column(name = "order_index")
    private Integer orderIndex;

    @OneToMany(mappedBy = "workoutExercise", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SetEntry> setEntries = new ArrayList<>();

    //No args constructor for JPA
    public WorkoutExercise() {}

    public WorkoutExercise(Workout workout, Exercise exercise, Integer orderIndex) {
        this.workout = workout;
        this.exercise = exercise;
        this.orderIndex = orderIndex;
    }

    // Getter for id
    public Long getId() {
        return id;
    }

    // Getter for workout
    public Workout getWorkout() {
        return workout;
    }

    // Setter for workout
    public void setWorkout(Workout workout) {
        this.workout = workout;
    }

    // Getter for exercise
    public Exercise getExercise() {
        return exercise;
    }

    // Setter for exercise
    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }

    // Getter for orderIndex
    public Integer getOrderIndex() {
        return orderIndex;
    }

    // Setter for orderIndex
    public void setOrderIndex(Integer orderIndex) {
        this.orderIndex = orderIndex;
    }

    // Getter for setEntries
    public List<SetEntry> getSetEntries() {
        return setEntries;
    }

    // Helper to add a set entry and keep both sides of the relationship in sync
    public void addSetEntry(SetEntry setEntry) {
        setEntries.add(setEntry);
        setEntry.setWorkoutExercise(this);
    }
}
