package com.github.kristoflemmens.acquisition.validation.cumulpayment;

import com.github.kristoflemmens.acquisition.AcquisitionRequestCommand;
import com.github.kristoflemmens.acquisition.AcquisitionRequestNumber;

public class ValidateCumulPayment extends AcquisitionRequestCommand {
    public ValidateCumulPayment(AcquisitionRequestNumber acquisitionRequestNumber) {
        super(acquisitionRequestNumber);
    }
}
