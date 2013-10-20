package com.github.kristoflemmens.acquisition.core.acquisitionrequest.validate.actorduplication;

import com.github.kristoflemmens.acquisition.core.acquisitionrequest.AcquisitionRequestCommand;
import com.github.kristoflemmens.acquisition.core.acquisitionrequest.AcquisitionRequestNumber;

public class ValidateActorDuplication extends AcquisitionRequestCommand {
    public ValidateActorDuplication(AcquisitionRequestNumber acquisitionRequestNumber) {
        super(acquisitionRequestNumber);
    }
}
