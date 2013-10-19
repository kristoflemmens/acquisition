package com.github.kristoflemmens.acquisition.core.validation.itineraexistence;

import com.github.kristoflemmens.acquisition.core.AcquisitionRequestEvent;
import com.github.kristoflemmens.acquisition.core.AcquisitionRequestNumber;

public class ItineraExistenceValidated extends AcquisitionRequestEvent {
    private final boolean existsInItinera;

    public ItineraExistenceValidated(AcquisitionRequestNumber acquisitionRequestNumber, int version, boolean existsInItinera) {
        super(acquisitionRequestNumber, version);
        this.existsInItinera = existsInItinera;
    }
}
