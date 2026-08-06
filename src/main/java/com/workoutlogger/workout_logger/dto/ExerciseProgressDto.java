package com.workoutlogger.workout_logger.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ExerciseProgressDto {

    private Long workoutId;
    private LocalDate date;
    private BigDecimal maxWeight;
    private int repsAtMaxWeight;
    private BigDecimal totalVolume;

    public ExerciseProgressDto(Long workoutId, LocalDate date, BigDecimal maxWeight, int repsAtMaxWeight, BigDecimal totalVolume) {
        this.workoutId = workoutId;
        this.date = date;
        this.maxWeight = maxWeight;
        this.repsAtMaxWeight = repsAtMaxWeight;
        this.totalVolume = totalVolume;
    }

    public Long getWorkoutId() {
        return workoutId;
    }

    public LocalDate getDate() {
        return date;
    }

    public BigDecimal getMaxWeight() {
        return maxWeight;
    }

    public int getRepsAtMaxWeight() {
        return repsAtMaxWeight;
    }

    public BigDecimal getTotalVolume() {
        return totalVolume;
    }
}
