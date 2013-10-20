package com.github.kristoflemmens.acquisition.core.acquisitionrequest.validate.itineraexistence;

import com.github.kristoflemmens.acquisition.core.acquisitionrequest.AcquisitionRequestEvent;
import com.github.kristoflemmens.acquisition.core.acquisitionrequest.AcquisitionRequestNumber;

public class ItineraExistenceValidated extends AcquisitionRequestEvent {
    private final boolean existsInItinera;

    public ItineraExistenceValidated(AcquisitionRequestNumber acquisitionRequestNumber, int version, boolean existsInItinera) {
        super(acquisitionRequestNumber, version);
        this.existsInItinera = existsInItinera;
    }
}
