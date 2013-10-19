package com.github.kristoflemmens.acquisition.eventsourcing;

import java.util.List;

public interface EventSourced {
    List<Event> unsavedEvents();

    EventId id();

    int version();
}
