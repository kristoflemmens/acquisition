package com.github.kristoflemmens.acquisition.core;

import com.github.kristoflemmens.acquisition.common.ValueObject;
import com.github.kristoflemmens.acquisition.eventsourcing.EventId;

import java.util.UUID;

public class AcquisitionRequestNumber extends ValueObject implements EventId {
    private final UUID id;

    public AcquisitionRequestNumber(UUID id) {
        this.id = id;
    }

}
