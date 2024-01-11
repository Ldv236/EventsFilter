package ru.ldv236;

import ru.ldv236.model.Activity;
import ru.ldv236.model.Event;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestUtils {

    public static List<Event> createEvents() {
        LocalDateTime threeDaysFromNow = LocalDateTime.now().plusDays(3);
        return Arrays.asList(
                createEvent("normal",
                        threeDaysFromNow, threeDaysFromNow.plusHours(2)),

                createEvent("normal multi activities",
                        threeDaysFromNow, threeDaysFromNow.plusHours(2),
                        threeDaysFromNow.plusHours(3), threeDaysFromNow.plusHours(5)),

                createEvent("begin in the past",
                        threeDaysFromNow.minusDays(6), threeDaysFromNow),

                createEvent("end before it begin",
                        threeDaysFromNow, threeDaysFromNow.minusHours(6)),

                createEvent("more than two hours interval",
                        threeDaysFromNow, threeDaysFromNow.plusHours(2),
                        threeDaysFromNow.plusHours(5), threeDaysFromNow.plusHours(6)),

                createEvent("more than two hours interval (2)",
                        threeDaysFromNow, threeDaysFromNow.plusHours(2),
                        threeDaysFromNow.plusHours(3), threeDaysFromNow.plusHours(4),
                        threeDaysFromNow.plusHours(6), threeDaysFromNow.plusHours(7)));
    }

    private static Event createEvent(final String description, final LocalDateTime... dates) {
        if ((dates.length % 2) != 0) {
            throw new IllegalArgumentException("you must pass an even number of dates");
        }
        List<Activity> activities = new ArrayList<>(dates.length / 2);
        for (int i = 0; i < (dates.length - 1); i += 2) {
            activities.add(new Activity(dates[i], dates[i + 1]));
        }
        return new Event(description, activities);
    }
}
