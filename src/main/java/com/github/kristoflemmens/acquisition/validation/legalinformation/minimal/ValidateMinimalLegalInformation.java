package com.github.kristoflemmens.acquisition.validation.legalinformation.minimal;

import com.github.kristoflemmens.acquisition.AcquisitionRequestCommand;
import com.github.kristoflemmens.acquisition.AcquisitionRequestNumber;

public class ValidateMinimalLegalInformation extends AcquisitionRequestCommand {
    public ValidateMinimalLegalInformation(AcquisitionRequestNumber acquisitionRequestNumber) {
        super(acquisitionRequestNumber);
    }
}
