package com.github.kristoflemmens.acquisition.core.acquisitionrequest.validate.data;

import com.github.kristoflemmens.acquisition.core.acquisitionrequest.AcquisitionRequestEvent;
import com.github.kristoflemmens.acquisition.core.acquisitionrequest.AcquisitionRequestNumber;
import com.github.kristoflemmens.acquisition.core.acquisitionrequest.validate.Validation;

import java.util.Set;

public class DataValidated extends AcquisitionRequestEvent {
    private final Set<Validation> validations;

    public DataValidated(AcquisitionRequestNumber acquisitionRequestNumber, int version, Set<Validation> validations) {
        super(acquisitionRequestNumber, version);
        this.validations = validations;
    }

    public boolean isDataValid() {
        return validations.isEmpty();
    }
}
