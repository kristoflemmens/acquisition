package com.github.kristoflemmens.acquisition.validation.actorduplication;

import com.github.kristoflemmens.acquisition.AcquisitionRequestEvent;
import com.github.kristoflemmens.acquisition.AcquisitionRequestNumber;
import com.github.kristoflemmens.acquisition.validation.Validation;

import java.util.Set;

public class ActorDuplicationValidated extends AcquisitionRequestEvent {
    private final Set<Validation> validations;

    public ActorDuplicationValidated(AcquisitionRequestNumber acquisitionRequestNumber, int version, Set<Validation> validations) {
        super(acquisitionRequestNumber, version);
        this.validations = validations;
    }
}
