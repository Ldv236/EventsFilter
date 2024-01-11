package ru.ldv236.model;

import java.util.List;
import java.util.stream.Collectors;

public class Event {

    private final String description;
    private final List<Activity> activities;

    public Event(final String description, final List<Activity> activities) {
        this.description = description;
        this.activities = activities;
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return description + ":\n" + activities.stream().map(Object::toString)
                .collect(Collectors.joining());
    }
}
