package ru.ldv236.service;

import ru.ldv236.TestUtils;
import ru.ldv236.filter.BeginInPastFilter;
import ru.ldv236.filter.EndBeforeBeginFilter;
import ru.ldv236.filter.EventFilter;
import ru.ldv236.filter.LongIntervalFilter;
import ru.ldv236.model.Event;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EventFilterServiceImplTest {

    EventFilterService eventFilterService = new EventFilterServiceImpl();
    List<Event> testEvents = TestUtils.createEvents();
    List<Event> filteredEvents;
    List<EventFilter> eventFilters = new ArrayList<>();

    @Test
    void testFilterEvents_DepartureInPastFilter() {

        BeginInPastFilter beginInPastFilter = new BeginInPastFilter();
        eventFilters.add(beginInPastFilter);

        filteredEvents = eventFilterService.filterEvents(testEvents, eventFilters);

        assertEquals(5, filteredEvents.size());
        assertFalse(filteredEvents.stream()
                .anyMatch(event -> event.getDescription().equals("begin in the past")));
    }

    @Test
    void testFilterEvents_ArrivalBeforeDepartureFilter() {

        EndBeforeBeginFilter endBeforeBeginFilter = new EndBeforeBeginFilter();
        eventFilters.add(endBeforeBeginFilter);

        filteredEvents = eventFilterService.filterEvents(testEvents, eventFilters);

        assertEquals(5, filteredEvents.size());
        assertFalse(filteredEvents.stream()
                .anyMatch(event -> event.getDescription().equals("end before it begin")));
    }

    @Test
    void testFilterEvents_LongGroundTimeFilter() {

        LongIntervalFilter longIntervalFilter = new LongIntervalFilter(2);
        eventFilters.add(longIntervalFilter);

        filteredEvents = eventFilterService.filterEvents(testEvents, eventFilters);

        assertEquals(4, filteredEvents.size());
        assertFalse(filteredEvents.stream()
                .anyMatch(event -> event.getDescription().equals("more than two hours interval")));
        assertFalse(filteredEvents.stream()
                .anyMatch(event -> event.getDescription().equals("more than two hours interval (2)")));
    }

    @Test
    void testFilterEvents_AllFilters() {

        BeginInPastFilter beginInPastFilter = new BeginInPastFilter();
        EndBeforeBeginFilter endBeforeBeginFilter = new EndBeforeBeginFilter();
        LongIntervalFilter longIntervalFilter = new LongIntervalFilter(2);
        eventFilters.add(beginInPastFilter);
        eventFilters.add(endBeforeBeginFilter);
        eventFilters.add(longIntervalFilter);

        filteredEvents = eventFilterService.filterEvents(testEvents, eventFilters);

        assertEquals(2, filteredEvents.size());
        assertTrue(filteredEvents.stream()
                .anyMatch(event -> event.getDescription().equals("normal")));
        assertTrue(filteredEvents.stream()
                .anyMatch(event -> event.getDescription().equals("normal multi activities")));
    }
}