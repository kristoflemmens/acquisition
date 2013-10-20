package com.github.kristoflemmens.acquisition.core.acquisitionrequest;

import com.github.kristoflemmens.acquisition.core.acquisitionrequest.create.AcquisitionRequestCreated;
import com.github.kristoflemmens.acquisition.core.file.File;
import com.github.kristoflemmens.acquisition.eventsourcing.Event;
import com.github.kristoflemmens.acquisition.eventsourcing.EventSourcedFactory;
import com.google.common.base.Optional;

import java.util.List;

import static com.github.kristoflemmens.acquisition.core.acquisitionrequest.AcquisitionRequest.Builder.newAcquisitionRequest;
import static com.google.common.collect.FluentIterable.from;

public class AcquisitionRequestFactory implements EventSourcedFactory<AcquisitionRequest> {

    public AcquisitionRequest create(AcquisitionRequestNumber acquisitionRequestNumber, String data) {
        return applyAcquisitionRequestCreated(new AcquisitionRequestCreated(acquisitionRequestNumber, 0, data));
    }

    @Override
    public AcquisitionRequest loadFromHistory(List<Event> history) {
        return init(from(history).first()).apply(from(history).skip(1)).markCommitted();
    }

    private AcquisitionRequest init(Optional<Event> event) {
        if (event.isPresent() && event.get() instanceof AcquisitionRequestCreated)
            return applyAcquisitionRequestCreated((AcquisitionRequestCreated) event.get());
        throw new UnsupportedOperationException("Cannot handle event " + event);
    }

    private AcquisitionRequest applyAcquisitionRequestCreated(AcquisitionRequestCreated acquisitionRequestCreated) {
        return newAcquisitionRequest()
                .withId(acquisitionRequestCreated.id())
                .withVersion(-1)
                .withUnsavedEvents(acquisitionRequestCreated)
                .withFile(new File(acquisitionRequestCreated.data()))
                .build();
    }
}
