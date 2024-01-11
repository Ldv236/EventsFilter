package ru.ldv236.filter;

import ru.ldv236.model.Activity;
import ru.ldv236.model.Event;

import java.time.Duration;
import java.util.List;

public class LongIntervalFilter implements EventFilter {

    private final long timeLimit;

    public LongIntervalFilter(long timeLimit) {
        this.timeLimit = timeLimit * 60;
    }

    @Override
    public boolean validate(Event event) {
        List<Activity> activities = event.getActivities();
        long totalGroundTime = 0;

        for (int i = 0; i < activities.size() - 1; i++) {
            Activity current = activities.get(i);
            Activity next = activities.get(i + 1);

            Duration groundTime = Duration.between(current.getEndDate(), next.getBeginDate());
            totalGroundTime += groundTime.toMinutes();
        }

        return totalGroundTime <= timeLimit;
    }
}
