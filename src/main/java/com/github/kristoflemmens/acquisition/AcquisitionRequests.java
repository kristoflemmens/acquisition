package com.github.kristoflemmens.acquisition;

import com.github.kristoflemmens.eventsourcing.EventSourcedCollection;
import com.github.kristoflemmens.eventsourcing.EventStore;

public class AcquisitionRequests implements EventSourcedCollection<AcquisitionRequest, AcquisitionRequestNumber> {

    private final EventStore eventStore;
    private final AcquisitionRequestFactory acquisitionRequestFactory;

    public AcquisitionRequests(EventStore eventStore, AcquisitionRequestFactory acquisitionRequestFactory) {
        this.eventStore = eventStore;
        this.acquisitionRequestFactory = acquisitionRequestFactory;
    }

    @Override
    public void save(AcquisitionRequest acquisitionRequest) {
        eventStore.save(acquisitionRequest);
    }

    @Override
    public AcquisitionRequest get(AcquisitionRequestNumber acquisitionRequestNumber) {
        return acquisitionRequestFactory.loadFromHistory(eventStore.eventsFor(acquisitionRequestNumber));
    }
}
