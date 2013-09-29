package com.github.kristoflemmens.acquisition;

import com.github.kristoflemmens.common.ValueObject;
import com.github.kristoflemmens.eventsourcing.Event;

public abstract class AcquisitionRequestEvent extends ValueObject implements Event {

    private final AcquisitionRequestNumber id;
    private final int version;

    protected AcquisitionRequestEvent(AcquisitionRequestNumber id, int version) {
        this.id = id;
        this.version = version;
    }

    public AcquisitionRequestNumber id() {
        return id;
    }

    @Override
    public int version() {
        return version;
    }
}
