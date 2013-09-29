package com.github.kristoflemmens.acquisition.validation.itineraexistence;

import com.github.kristoflemmens.acquisition.AcquisitionRequestEvent;
import com.github.kristoflemmens.acquisition.AcquisitionRequestNumber;

public class ItineraExistenceValidated extends AcquisitionRequestEvent {
    private final boolean existsInItinera;

    public ItineraExistenceValidated(AcquisitionRequestNumber acquisitionRequestNumber, int version, boolean existsInItinera) {
        super(acquisitionRequestNumber, version);
        this.existsInItinera = existsInItinera;
    }
}
