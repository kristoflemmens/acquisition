package com.github.kristoflemmens.acquisition.core.acquisitionrequest;

import com.github.kristoflemmens.acquisition.common.ValueObject;
import com.github.kristoflemmens.acquisition.eventsourcing.Event;

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
