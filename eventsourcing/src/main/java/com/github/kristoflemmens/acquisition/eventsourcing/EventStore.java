package com.github.kristoflemmens.acquisition.eventsourcing;

import java.util.List;

public interface EventStore {

    List<Event> eventsFor(EventId identifier);

    void save(EventSourced eventSourced);

    void register(EventListener eventListener);
}
