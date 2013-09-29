package com.github.kristoflemmens.eventsourcing;

import com.github.kristoflemmens.acquisition.AcquisitionRequest;

public interface EventSourcedCollection<EVENT_SOURCED extends EventSourced, EVENT_ID extends EventId> {
    void save(EVENT_SOURCED eventSourced);

    AcquisitionRequest get(EVENT_ID id);
}
