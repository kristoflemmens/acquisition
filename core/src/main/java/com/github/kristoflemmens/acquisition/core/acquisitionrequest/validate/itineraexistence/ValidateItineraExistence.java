package com.github.kristoflemmens.acquisition.core.acquisitionrequest.validate.itineraexistence;

import com.github.kristoflemmens.acquisition.core.acquisitionrequest.AcquisitionRequestCommand;
import com.github.kristoflemmens.acquisition.core.acquisitionrequest.AcquisitionRequestNumber;

public class ValidateItineraExistence extends AcquisitionRequestCommand {
    public ValidateItineraExistence(AcquisitionRequestNumber acquisitionRequestNumber) {
        super(acquisitionRequestNumber);
    }
}
