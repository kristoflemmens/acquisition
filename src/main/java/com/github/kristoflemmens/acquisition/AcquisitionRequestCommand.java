package com.github.kristoflemmens.acquisition;

import com.github.kristoflemmens.common.ValueObject;
import com.github.kristoflemmens.messaging.Command;

public abstract class AcquisitionRequestCommand extends ValueObject implements Command {
    private final AcquisitionRequestNumber acquisitionRequestNumber;

    protected AcquisitionRequestCommand(AcquisitionRequestNumber acquisitionRequestNumber) {
        this.acquisitionRequestNumber = acquisitionRequestNumber;
    }

    public AcquisitionRequestNumber acquisitionRequestNumber() {
        return acquisitionRequestNumber;
    }
}
