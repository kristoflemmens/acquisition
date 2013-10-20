package com.github.kristoflemmens.acquisition.core.acquisitionrequest.validate.data;

import com.github.kristoflemmens.acquisition.core.acquisitionrequest.AcquisitionRequestCommand;
import com.github.kristoflemmens.acquisition.core.acquisitionrequest.AcquisitionRequestNumber;

public class ValidateData extends AcquisitionRequestCommand {

    public ValidateData(AcquisitionRequestNumber acquisitionRequestNumber) {
        super(acquisitionRequestNumber);
    }
}
