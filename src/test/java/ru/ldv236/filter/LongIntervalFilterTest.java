package ru.ldv236.filter;

import ru.ldv236.model.Activity;
import ru.ldv236.model.Event;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LongIntervalFilterTest {

    EventFilter filter = new LongIntervalFilter(2);
    LocalDateTime now = LocalDateTime.now();

    @Test
    void testLongIntervalFilter_validEvent() {

        Activity activity1 = new Activity(now.plusHours(1), now.plusHours(2));
        Activity activity2 = new Activity(now.plusHours(3), now.plusHours(4));
        List<Activity> activities = List.of(activity1, activity2);
        Event validEvent = new Event("event", activities);
        assertTrue(filter.validate(validEvent));
    }

    @Test
    void testLongIntervalFilter_validEvent_WithMinutes() {

        Activity activity1 = new Activity(now.plusMinutes(0), now.plusMinutes(100));
        Activity activity2 = new Activity(now.plusMinutes(220), now.plusMinutes(300));
        List<Activity> activities = List.of(activity1, activity2);
        Event validEvent = new Event("event", activities);
        assertTrue(filter.validate(validEvent));
    }

    @Test
    void testLongIntervalFilter_invalidEventTwoActivities() {

        Activity activity1 = new Activity(now.plusHours(1), now.plusHours(2));
        Activity activity2 = new Activity(now.plusHours(5), now.plusHours(6));
        List<Activity> activities = List.of(activity1, activity2);
        Event validEvent = new Event("event", activities);
        assertFalse(filter.validate(validEvent));
    }

    @Test
    void testLongIntervalFilter_invalidEventTotalTimeLimit() {

        Activity activity1 = new Activity(now.plusHours(1), now.plusHours(2));
        Activity activity2 = new Activity(now.plusHours(3), now.plusHours(4));
        Activity activity3 = new Activity(now.plusHours(5), now.plusHours(6));
        Activity activity4 = new Activity(now.plusHours(7), now.plusHours(8));
        List<Activity> activities = List.of(activity1, activity2, activity3, activity4);
        Event validEvent = new Event("event", activities);
        assertFalse(filter.validate(validEvent));
    }

    @Test
    void testLongIntervalFilter_InvalidEventTotalTimeLimit_WithMinutes() {

        Activity activity1 = new Activity(now.plusMinutes(0), now.plusMinutes(40));
        Activity activity2 = new Activity(now.plusMinutes(80), now.plusMinutes(120));
        Activity activity3 = new Activity(now.plusMinutes(160), now.plusMinutes(200));
        Activity activity4 = new Activity(now.plusMinutes(241), now.plusMinutes(281));
        List<Activity> activities = List.of(activity1, activity2, activity3, activity4);
        Event validEvent = new Event("event", activities);
        assertFalse(filter.validate(validEvent));
    }
}