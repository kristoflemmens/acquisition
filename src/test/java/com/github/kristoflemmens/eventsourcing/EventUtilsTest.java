package com.github.kristoflemmens.eventsourcing;

import com.github.kristoflemmens.common.ValueObject;
import com.github.kristoflemmens.testutils.UnitTestCase;
import com.google.common.collect.Lists;
import org.junit.Test;

import static com.github.kristoflemmens.eventsourcing.EventUtils.sort;
import static com.github.kristoflemmens.eventsourcing.EventUtils.versionOfLastEvent;
import static com.google.common.collect.Lists.newArrayList;
import static org.fest.assertions.api.Assertions.assertThat;

public class EventUtilsTest extends UnitTestCase {

    private final Event aDummyEvent = new DummyEvent(2);
    private final Event anotherDummyEvent = new DummyEvent(1);

    @Test
    public void versionOfLastEvent_ReturnsLastVersion() throws Exception {
        assertThat(versionOfLastEvent(newArrayList(aDummyEvent, anotherDummyEvent))).isEqualTo(2);
    }

    @Test
    public void versionOfLastEvent_NoEvents() throws Exception {
        assertThat(versionOfLastEvent(Lists.<Event>newArrayList())).isEqualTo(-1);
    }

    @Test
    public void sort_SortsOnVersion() throws Exception {
        assertThat(sort(newArrayList(aDummyEvent, anotherDummyEvent))).containsExactly(anotherDummyEvent, aDummyEvent);
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

}
