package ru.ldv236.service;

import ru.ldv236.filter.EventFilter;
import ru.ldv236.model.Event;

import java.util.List;

public class EventFilterServiceImpl implements EventFilterService {

    public List<Event> filterEvents(List<Event> events, List<EventFilter> filters) {
        return events.stream()
                .filter(event -> filters.stream().allMatch(filter -> filter.validate(event)))
                .toList();
    }

    public void sendFilteredEvents(List<Event> events) {
        //test implementation of send method
        for (Event event : events) {
            System.out.println(event);
        }
    }
}
