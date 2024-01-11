package ru.ldv236.filter;

import java.util.ArrayList;
import java.util.List;

public class EventFilterContext {

    private List<EventFilter> filters;

    public EventFilterContext() {
        this.filters = new ArrayList<>();
    }

    public void setFilter(EventFilter filter) {
        this.filters.add(filter);
    }

    public void deleteFilter(EventFilter filter) {
        this.filters.remove(filter);
    }

    public void clearFilters() {
        this.filters.clear();
    }

    public List<EventFilter> getFilters() {
        return filters;
    }
}
