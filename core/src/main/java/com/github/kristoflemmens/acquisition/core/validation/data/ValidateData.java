package com.github.kristoflemmens.acquisition.core.validation.data;

import com.github.kristoflemmens.acquisition.core.AcquisitionRequestCommand;
import com.github.kristoflemmens.acquisition.core.AcquisitionRequestNumber;

public class ValidateData extends AcquisitionRequestCommand {

    public ValidateData(AcquisitionRequestNumber acquisitionRequestNumber) {
        super(acquisitionRequestNumber);
    }
}
