package com.github.kristoflemmens.acquisition.validation.itineraexistence;

import com.github.kristoflemmens.acquisition.AcquisitionRequestCommand;
import com.github.kristoflemmens.acquisition.AcquisitionRequestNumber;

public class ValidateItineraExistence extends AcquisitionRequestCommand {
    public ValidateItineraExistence(AcquisitionRequestNumber acquisitionRequestNumber) {
        super(acquisitionRequestNumber);
    }
}
