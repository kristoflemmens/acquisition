package com.github.kristoflemmens.acquisition.core.acquisitionrequest.validate.legalinformation.minimal;

import com.github.kristoflemmens.acquisition.core.acquisitionrequest.AcquisitionRequestCommand;
import com.github.kristoflemmens.acquisition.core.acquisitionrequest.AcquisitionRequestNumber;

public class ValidateMinimalLegalInformation extends AcquisitionRequestCommand {
    public ValidateMinimalLegalInformation(AcquisitionRequestNumber acquisitionRequestNumber) {
        super(acquisitionRequestNumber);
    }
}
