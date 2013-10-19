package com.github.kristoflemmens.acquisition.core;

import com.github.kristoflemmens.acquisition.common.ValueObject;
import com.github.kristoflemmens.acquisition.eventsourcing.Event;
import com.github.kristoflemmens.acquisition.eventsourcing.EventId;
import com.github.kristoflemmens.acquisition.eventsourcing.EventSourced;
import com.github.kristoflemmens.acquisition.eventsourcing.EventStore;
import com.github.kristoflemmens.acquisition.testutils.UnitTestCase;
import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.List;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.fest.assertions.api.Assertions.fail;

public class EventStoreTest extends UnitTestCase {

    private final DummyEventId identifier = new DummyEventId();
    private final DummyEvent event = new DummyEvent(0);
    private final DummyEvent otherEvent = new DummyEvent(1);
    private final EventStore eventStore = InMemoryEventStore.inMemoryEventStore();

    @Test
    public void save_NewEventSourcedHasVersionMinus1() throws Exception {
        eventStore.save(new DummyEventSourced(identifier, -1, Lists.<Event>newArrayList(event, otherEvent)));

        assertThat(eventStore.eventsFor(identifier)).containsExactly(event, otherEvent);
    }

    @Test
    public void save_ExistingEventSourcedHasVersionOfPreviousEvent() throws Exception {
        eventStore.save(new DummyEventSourced(identifier, -1, Lists.<Event>newArrayList(event)));
        eventStore.save(new DummyEventSourced(identifier, 0, Lists.<Event>newArrayList(otherEvent)));

        assertThat(eventStore.eventsFor(identifier)).containsExactly(event, otherEvent);
    }

    @Test
    public void save_EventSourcedHasStillOldVersion() throws Exception {
        eventStore.save(new DummyEventSourced(identifier, -1, Lists.<Event>newArrayList(event)));

        try {
            eventStore.save(new DummyEventSourced(identifier, -1, Lists.<Event>newArrayList(otherEvent)));
            fail("Expected IllegalStateException with message Concurrency problem - Event has wrong version. Version is -1 but should be 0");
        } catch (Exception e) {
            assertThat(e)
                    .isInstanceOf(IllegalStateException.class)
                    .hasMessage("Concurrency problem - Event has wrong version. Version is -1 but should be 0");
        }
    }

    private static class DummyEvent extends ValueObject implements Event {

        private final int version;

        DummyEvent(int version) {
            this.version = version;
        }

        @Override
        public int version() {
            return version;
        }
    }

    private static class DummyEventId extends ValueObject implements EventId {
    }

    private static class DummyEventSourced extends ValueObject implements EventSourced {
        private final List<Event> unsavedEvents;
        private final DummyEventId id;
        private final int version;

        public DummyEventSourced(DummyEventId id, int version, List<Event> unsavedEvents) {
            this.unsavedEvents = unsavedEvents;
            this.id = id;
            this.version = version;
        }

        @Override
        public List<Event> unsavedEvents() {
            return unsavedEvents;
        }

        @Override
        public DummyEventId id() {
            return id;
        }

        @Override
        public int version() {
            return version;
        }
    }
}
