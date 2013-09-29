package com.github.kristoflemmens.eventsourcing;

import java.util.List;

public interface EventSourced {
    List<Event> unsavedEvents();

    EventId id();

    int version();
}
