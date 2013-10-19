package com.github.kristoflemmens.acquisition.core.validation.legalinformation.minimal;

import com.github.kristoflemmens.acquisition.core.AcquisitionRequestEvent;
import com.github.kristoflemmens.acquisition.core.AcquisitionRequestNumber;
import com.github.kristoflemmens.acquisition.core.validation.Validation;

import java.util.Set;

public class MinimalLegalInformationValidated extends AcquisitionRequestEvent {
    private final Set<Validation> validations;

    public MinimalLegalInformationValidated(AcquisitionRequestNumber acquisitionRequestNumber, int version, Set<Validation> validations) {
        super(acquisitionRequestNumber, version);
        this.validations = validations;
    }
}
