package com.github.kristoflemmens.acquisition.eventsourcing;

import com.google.common.base.Function;
import com.google.common.base.Optional;

import java.util.List;

import static com.google.common.collect.FluentIterable.from;
import static com.google.common.collect.Ordering.natural;

public class EventUtils {
    public static int versionOfLastEvent(Iterable<Event> events) {
        Optional<Event> last = from(sort(events)).last();

        return last.isPresent() ? last.get().version() : -1;
    }

    public static List<Event> sort(Iterable<Event> events) {
        return natural().onResultOf(version()).sortedCopy(events);
    }

    private static Function<Event, Integer> version() {
        return new Function<Event, Integer>() {
            @Override
            public Integer apply(Event Event) {
                return Event.version();
            }
        };
    }
}
