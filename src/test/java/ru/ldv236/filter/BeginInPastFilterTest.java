package ru.ldv236.filter;

import ru.ldv236.model.Activity;
import ru.ldv236.model.Event;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BeginInPastFilterTest {

    EventFilter filter = new BeginInPastFilter();
    LocalDateTime now = LocalDateTime.now();

    @Test
    void testBeginInPastFilter_validEvent() {

        Activity validActivity1 = new Activity(now.plusHours(1), now.plusHours(2));
        Activity validActivity2 = new Activity(now.plusHours(1), now.plusHours(3));
        List<Activity> activities = List.of(validActivity1, validActivity2);
        Event validEvent = new Event("event", activities);
        assertTrue(filter.validate(validEvent));
    }

    @Test
    void testBeginInPastFilter_invalidEvent() {

        Activity validActivity = new Activity(now.plusHours(1), now.plusHours(2));
        Activity invalidActivity = new Activity(now.minusHours(1), now.plusHours(2));
        List<Activity> activities = List.of(validActivity, invalidActivity);
        Event validEvent = new Event("event", activities);
        assertFalse(filter.validate(validEvent));
    }
}