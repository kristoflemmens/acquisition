package com.github.kristoflemmens.acquisition.validation.actorduplication;

import com.github.kristoflemmens.acquisition.AcquisitionRequestCommand;
import com.github.kristoflemmens.acquisition.AcquisitionRequestNumber;

public class ValidateActorDuplication extends AcquisitionRequestCommand {
    public ValidateActorDuplication(AcquisitionRequestNumber acquisitionRequestNumber) {
        super(acquisitionRequestNumber);
    }
}
