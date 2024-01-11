package ru.ldv236.filter;

import ru.ldv236.model.Event;

public class EndBeforeBeginFilter implements EventFilter {

    @Override
    public boolean validate(Event event) {
        return event.getActivities().stream()
                .noneMatch(activity -> activity.getEndDate().isBefore(activity.getBeginDate()));
    }
}
