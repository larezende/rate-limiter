package io.group.ratelimiter.infra.model;

import java.time.LocalTime;

public class TimeSchedule {

    private final LocalTime start;
    private final LocalTime end;

    public TimeSchedule(String start, String end) {
        this.start = LocalTime.parse(start);
        this.end = LocalTime.parse(end);
    }

    public boolean isTimeInSchedule(LocalTime time) {
        if (end.isAfter(start)) {
            return time.isAfter(start) && time.isBefore(end);
        } else {
            return time.isAfter(start) || time.isBefore(end);
        }
    }

}
