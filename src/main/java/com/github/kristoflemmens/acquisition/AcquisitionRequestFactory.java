package com.github.kristoflemmens.acquisition;

import com.github.kristoflemmens.acquisition.requestDocument.RequestDocument;
import com.github.kristoflemmens.acquisition.start.AcquisitionRequestCreated;
import com.github.kristoflemmens.eventsourcing.Event;
import com.github.kristoflemmens.eventsourcing.EventSourcedFactory;

import java.util.List;

import static com.github.kristoflemmens.acquisition.AcquisitionRequest.Builder.newAcquisitionRequest;
import static com.google.common.collect.Iterables.getFirst;
import static com.google.common.collect.Iterables.skip;

public class AcquisitionRequestFactory implements EventSourcedFactory<AcquisitionRequest> {

    public AcquisitionRequest create(AcquisitionRequestNumber acquisitionRequestNumber, String data) {
        return applyAcquisitionRequestCreated(new AcquisitionRequestCreated(acquisitionRequestNumber, 0, data));
    }

    @Override
    public AcquisitionRequest loadFromHistory(List<Event> history) {
        return init(getFirst(history, null)).apply(skip(history, 1)).markCommitted();
    }

    private AcquisitionRequest init(Event event) {
        if (event != null && event instanceof AcquisitionRequestCreated)
            return applyAcquisitionRequestCreated((AcquisitionRequestCreated) event);
        throw new UnsupportedOperationException("Cannot handle event " + event);
    }

    private AcquisitionRequest applyAcquisitionRequestCreated(AcquisitionRequestCreated acquisitionRequestCreated) {
        return newAcquisitionRequest()
                .withId(acquisitionRequestCreated.id())
                .withVersion(-1)
                .withUnsavedEvents(acquisitionRequestCreated)
                .withRequestDocument(new RequestDocument(acquisitionRequestCreated.data()))
                .build();
    }
}
