package ru.ldv236.service;

import ru.ldv236.filter.EventFilter;
import ru.ldv236.model.Event;

import java.util.List;

public interface EventFilterService {

    List<Event> filterEvents(List<Event> events, List<EventFilter> filters);

    void sendFilteredEvents(List<Event> events);
}
