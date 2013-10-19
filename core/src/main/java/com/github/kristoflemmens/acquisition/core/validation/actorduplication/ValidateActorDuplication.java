package com.github.kristoflemmens.acquisition.core.validation.actorduplication;

import com.github.kristoflemmens.acquisition.core.AcquisitionRequestCommand;
import com.github.kristoflemmens.acquisition.core.AcquisitionRequestNumber;

public class ValidateActorDuplication extends AcquisitionRequestCommand {
    public ValidateActorDuplication(AcquisitionRequestNumber acquisitionRequestNumber) {
        super(acquisitionRequestNumber);
    }
}
