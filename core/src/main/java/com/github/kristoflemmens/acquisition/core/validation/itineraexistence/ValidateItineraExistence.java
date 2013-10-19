package com.github.kristoflemmens.acquisition.core.validation.itineraexistence;

import com.github.kristoflemmens.acquisition.core.AcquisitionRequestCommand;
import com.github.kristoflemmens.acquisition.core.AcquisitionRequestNumber;

public class ValidateItineraExistence extends AcquisitionRequestCommand {
    public ValidateItineraExistence(AcquisitionRequestNumber acquisitionRequestNumber) {
        super(acquisitionRequestNumber);
    }
}
