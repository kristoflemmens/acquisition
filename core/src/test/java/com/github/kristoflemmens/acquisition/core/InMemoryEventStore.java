package com.github.kristoflemmens.acquisition.core;

import com.github.kristoflemmens.acquisition.eventsourcing.*;
import com.google.common.collect.Multimap;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import org.slf4j.Logger;

import java.util.List;

import static com.github.kristoflemmens.acquisition.eventsourcing.EventUtils.sort;
import static com.github.kristoflemmens.acquisition.eventsourcing.EventUtils.versionOfLastEvent;
import static com.google.common.base.Preconditions.checkState;
import static com.google.common.collect.ArrayListMultimap.create;
import static java.lang.String.format;
import static org.slf4j.LoggerFactory.getLogger;

public class InMemoryEventStore implements EventStore {
    private static final String ERROR_MESSAGE = "Concurrency problem - Event has wrong version. Version is %d but should be %d";
    private final Multimap<EventId, Event> eventsMap;
    private final EventBus eventBus;

    private InMemoryEventStore(Multimap<EventId, Event> eventsMap, EventBus eventBus) {
        this.eventsMap = eventsMap;
        this.eventBus = eventBus;
    }

    public static InMemoryEventStore inMemoryEventStore() {
        return new InMemoryEventStore(eventsMap(), eventBus());
    }

    private static Multimap<EventId, Event> eventsMap() {
        return create();
    }

    private static EventBus eventBus() {
        EventBus eventBus = new EventBus("events");
        eventBus.register(new EventLogger());
        return eventBus;
    }

    @Override
    public List<Event> eventsFor(EventId id) {
        return sort(eventsMap.get(id));
    }

    @Override
    public void save(EventSourced eventSourced) {
        checkState(noConcurrencyProblemsWith(eventSourced), errorMessage(eventSourced));
        for (Event event : eventSourced.unsavedEvents()) {
            eventsMap.put(eventSourced.id(), event);
            eventBus.post(event);
        }
    }

    private boolean noConcurrencyProblemsWith(EventSourced eventSourced) {
        return lastVersionOf(eventSourced) == eventSourced.version();
    }

    private String errorMessage(EventSourced eventSourced) {
        return format(ERROR_MESSAGE, eventSourced.version(), lastVersionOf(eventSourced));
    }

    @Override
    public void register(EventListener eventListener) {
        eventBus.register(eventListener);
    }

    private int lastVersionOf(EventSourced eventSourced) {
        return versionOfLastEvent(eventsMap.get(eventSourced.id()));
    }

    private static class EventLogger {
        private static final Logger LOGGER = getLogger(InMemoryEventStore.class);

        @Subscribe
        public void handle(Event event) {
            LOGGER.info("Handling event {}", event);
        }
    }

}
