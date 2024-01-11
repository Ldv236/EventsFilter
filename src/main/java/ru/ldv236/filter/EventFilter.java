package ru.ldv236.filter;

import ru.ldv236.model.Event;

public interface EventFilter {

    boolean validate(Event event);
}
