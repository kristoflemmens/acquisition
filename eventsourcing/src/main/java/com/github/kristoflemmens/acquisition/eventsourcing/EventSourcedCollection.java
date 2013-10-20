package com.github.kristoflemmens.acquisition.eventsourcing;

public interface EventSourcedCollection<EVENT_SOURCED extends EventSourced, EVENT_ID extends EventId> {
    void save(EVENT_SOURCED eventSourced);

    EVENT_SOURCED get(EVENT_ID id);
}
