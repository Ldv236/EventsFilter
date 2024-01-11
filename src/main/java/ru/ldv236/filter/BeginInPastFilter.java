package ru.ldv236.filter;

import ru.ldv236.model.Event;

import java.time.LocalDateTime;

public class BeginInPastFilter implements EventFilter {

    @Override
    public boolean validate(Event event) {
        return event.getActivities().stream()
                .noneMatch(activity -> activity.getBeginDate().isBefore(LocalDateTime.now()));
    }
}
