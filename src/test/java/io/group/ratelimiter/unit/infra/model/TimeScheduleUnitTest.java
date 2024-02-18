package io.group.ratelimiter.unit.infra.model;

import io.group.ratelimiter.infra.model.TimeSchedule;
import io.group.ratelimiter.unit.AbstractUnitTest;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TimeScheduleUnitTest extends AbstractUnitTest {

    private TimeSchedule timeSchedule;

    @Test
    void isTimeInSchedule_WhenTimeIsInNormalSchedule_ShouldReturnTrue() {
        timeSchedule = new TimeSchedule("08:00", "17:00");
        boolean result = timeSchedule.isTimeInSchedule(LocalTime.parse("12:00"));
        assertTrue(result);
    }

    @Test
    void isTimeInSchedule_WhenTimeIsInInvertedSchedule_ShouldReturnTrue() {
        timeSchedule = new TimeSchedule("17:00", "08:00");
        boolean result = timeSchedule.isTimeInSchedule(LocalTime.parse("00:00"));
        assertTrue(result);
    }

    @Test
    void isTimeInSchedule_WhenTimeIsNotInNormalSchedule_ShouldReturnFalse() {
        timeSchedule = new TimeSchedule("08:00", "17:00");
        boolean result = timeSchedule.isTimeInSchedule(LocalTime.parse("00:00"));
        assertFalse(result);
    }

    @Test
    void isTimeInSchedule_WhenTimeIsNotInInvertedSchedule_ShouldReturnFalse() {
        timeSchedule = new TimeSchedule("17:00", "08:00");
        boolean result = timeSchedule.isTimeInSchedule(LocalTime.parse("12:00"));
        assertFalse(result);
    }

}