package com.github.kristoflemmens.acquisition.validation.data;

import com.github.kristoflemmens.acquisition.AcquisitionRequestCommand;
import com.github.kristoflemmens.acquisition.AcquisitionRequestNumber;

public class ValidateData extends AcquisitionRequestCommand {

    public ValidateData(AcquisitionRequestNumber acquisitionRequestNumber) {
        super(acquisitionRequestNumber);
    }
}
