package com.github.kristoflemmens.acquisition.eventsourcing;

import java.util.List;

public interface EventSourcedFactory<EVENT_SOURCED extends EventSourced> {

    EVENT_SOURCED loadFromHistory(List<Event> history);

}
