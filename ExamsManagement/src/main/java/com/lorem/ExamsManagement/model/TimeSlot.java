package com.lorem.ExamsManagement.model;

import java.time.LocalTime;

public enum TimeSlot {
    AN(LocalTime.of(8, 30), LocalTime.of(12, 30)),
    FN(LocalTime.of(14, 30), LocalTime.of(18, 30));

    private final LocalTime startTime;
    private final LocalTime endTime;

    TimeSlot(LocalTime startTime, LocalTime endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }
}
