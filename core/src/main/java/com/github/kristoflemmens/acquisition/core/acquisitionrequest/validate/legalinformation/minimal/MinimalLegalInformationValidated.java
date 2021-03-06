package com.github.kristoflemmens.acquisition.core.acquisitionrequest.validate.legalinformation.minimal;

import com.github.kristoflemmens.acquisition.core.acquisitionrequest.AcquisitionRequestEvent;
import com.github.kristoflemmens.acquisition.core.acquisitionrequest.AcquisitionRequestNumber;
import com.github.kristoflemmens.acquisition.core.acquisitionrequest.validate.Validation;

import java.util.Set;

public class MinimalLegalInformationValidated extends AcquisitionRequestEvent {
    private final Set<Validation> validations;

    public MinimalLegalInformationValidated(AcquisitionRequestNumber acquisitionRequestNumber, int version, Set<Validation> validations) {
        super(acquisitionRequestNumber, version);
        this.validations = validations;
    }
}
