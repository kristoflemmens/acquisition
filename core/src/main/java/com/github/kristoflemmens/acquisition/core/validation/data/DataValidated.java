package com.github.kristoflemmens.acquisition.core.validation.data;

import com.github.kristoflemmens.acquisition.core.AcquisitionRequestEvent;
import com.github.kristoflemmens.acquisition.core.AcquisitionRequestNumber;
import com.github.kristoflemmens.acquisition.core.validation.Validation;

import java.util.Set;

public class DataValidated extends AcquisitionRequestEvent {
    private final Set<Validation> validations;

    public DataValidated(AcquisitionRequestNumber acquisitionRequestNumber, int version, Set<Validation> validations) {
        super(acquisitionRequestNumber, version);
        this.validations = validations;
    }
}
