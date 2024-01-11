package ru.ldv236;

import ru.ldv236.filter.BeginInPastFilter;
import ru.ldv236.filter.EndBeforeBeginFilter;
import ru.ldv236.filter.EventFilterContext;
import ru.ldv236.filter.LongIntervalFilter;
import ru.ldv236.generate.EventBuilder;
import ru.ldv236.model.Event;
import ru.ldv236.service.EventFilterService;
import ru.ldv236.service.EventFilterServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        EventFilterService eventFilterService = new EventFilterServiceImpl();
        EventFilterContext eventFilters = new EventFilterContext();

        List<Event> testEvents = EventBuilder.createEvents();
        List<Event> filteredEvents;

        System.out.println("**********\ndemo - apply beginInPastFilter\n");
        BeginInPastFilter beginInPastFilter = new BeginInPastFilter();
        eventFilters.setFilter(beginInPastFilter);
        filteredEvents = eventFilterService.filterEvents(testEvents, eventFilters.getFilters());
        eventFilterService.sendFilteredEvents(filteredEvents);

        System.out.println("**********\ndemo - apply endBeforeBeginFilter\n");
        eventFilters.deleteFilter(beginInPastFilter);
        EndBeforeBeginFilter endBeforeBeginFilter = new EndBeforeBeginFilter();
        eventFilters.setFilter(endBeforeBeginFilter);
        filteredEvents = eventFilterService.filterEvents(testEvents, eventFilters.getFilters());
        eventFilterService.sendFilteredEvents(filteredEvents);

        System.out.println("**********\ndemo - apply longIntervalFilter\n");
        eventFilters.deleteFilter(endBeforeBeginFilter);
        LongIntervalFilter longIntervalFilter = new LongIntervalFilter(2);
        eventFilters.setFilter(longIntervalFilter);
        filteredEvents = eventFilterService.filterEvents(testEvents, eventFilters.getFilters());
        eventFilterService.sendFilteredEvents(filteredEvents);

        System.out.println("**********\ndemo - apply all filters\n");
        eventFilters.clearFilters();
        eventFilters.setFilter(beginInPastFilter);
        eventFilters.setFilter(endBeforeBeginFilter);
        eventFilters.setFilter(longIntervalFilter);
        filteredEvents = eventFilterService.filterEvents(testEvents, eventFilters.getFilters());
        eventFilterService.sendFilteredEvents(filteredEvents);
    }
}