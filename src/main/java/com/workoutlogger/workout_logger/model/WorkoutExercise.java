package com.workoutlogger.workout_logger.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the association between a workout and an exercise
 * A WorkoutExercise identifies which exercise belongs to a workout, its position within the workout,
 * and the sets performed for that exercise
 */
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

    /**
     * No args constructor for JPA
     */
    public WorkoutExercise() {}

    /**
     * Creates a WorkoutExercise with a workout, exercise, order of index
     * @param workout the workout to be joined
     * @param exercise the exercise to be joined
     * @param orderIndex position of the exercise within the workout
     */
    public WorkoutExercise(Workout workout, Exercise exercise, Integer orderIndex) {
        this.workout = workout;
        this.exercise = exercise;
        this.orderIndex = orderIndex;
    }

    /**
     * Returns the identifier for the WorkoutExercise
     * @return the id for WorkoutExercise
     */
    public Long getId() {
        return id;
    }

    /**
     * Returns the workout associated with the WorkoutExercise
     * @return the workout associated with the WorkoutExercise
     */
    public Workout getWorkout() {
        return workout;
    }

    /**
     * Sets the workout associated with the WorkoutExercise
     * @param workout the workout that will be set
     */
    public void setWorkout(Workout workout) {
        this.workout = workout;
    }

    /**
     * Returns the exercise associated with this workout entry
     * @return the exercise
     */
    public Exercise getExercise() {
        return exercise;
    }

    /**
     * Sets the exercise associated with the WorkoutExercise
     * @param exercise what is being set
     */
    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }

    /**
     * Returns the orderIndex associated with the exercise within the workout
     * @return the exercise's position in the workout
     */
    public Integer getOrderIndex() {
        return orderIndex;
    }

    /**
     * Sets the position of the exercise within the workout
     * @param orderIndex the exercise's position in the workout
     */
    public void setOrderIndex(Integer orderIndex) {
        this.orderIndex = orderIndex;
    }

    /**
     * Returns the set entries associated with this workout exercise
     * @return the list of set entries
     */
    public List<SetEntry> getSetEntries() {
        return setEntries;
    }

    /**
     * Helper method that will add the set entry to this workout exercise
     * Establishes the relationship from set entry back to WorkoutExercise
     * @param setEntry the set entry that will be added
     */
    public void addSetEntry(SetEntry setEntry) {
        setEntries.add(setEntry);
        setEntry.setWorkoutExercise(this);
    }
}
