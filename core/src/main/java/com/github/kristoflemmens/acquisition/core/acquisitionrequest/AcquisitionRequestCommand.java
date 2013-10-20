package com.github.kristoflemmens.acquisition.core.acquisitionrequest;

import com.github.kristoflemmens.acquisition.common.ValueObject;
import com.github.kristoflemmens.acquisition.messaging.Command;

public abstract class AcquisitionRequestCommand extends ValueObject implements Command {
    private final AcquisitionRequestNumber acquisitionRequestNumber;

    protected AcquisitionRequestCommand(AcquisitionRequestNumber acquisitionRequestNumber) {
        this.acquisitionRequestNumber = acquisitionRequestNumber;
    }

    public AcquisitionRequestNumber acquisitionRequestNumber() {
        return acquisitionRequestNumber;
    }
}
