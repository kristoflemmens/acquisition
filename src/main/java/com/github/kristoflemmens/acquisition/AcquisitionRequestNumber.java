package com.github.kristoflemmens.acquisition;

import com.github.kristoflemmens.common.ValueObject;
import com.github.kristoflemmens.eventsourcing.EventId;

import java.util.UUID;

public class AcquisitionRequestNumber extends ValueObject implements EventId {
    private final UUID id;

    public AcquisitionRequestNumber(UUID id) {
        this.id = id;
    }

}
