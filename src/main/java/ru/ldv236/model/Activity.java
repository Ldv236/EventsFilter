package ru.ldv236.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Activity {
    private final LocalDateTime beginDate;

    private final LocalDateTime endDate;

    public Activity(final LocalDateTime begin, final LocalDateTime end) {
        beginDate = Objects.requireNonNull(begin);
        endDate = Objects.requireNonNull(end);
    }

    public LocalDateTime getBeginDate() {
        return beginDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    @Override
    public String toString() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        return '[' + beginDate.format(fmt) + '|' + endDate.format(fmt) + "]\n";
    }
}
