package com.github.kristoflemmens.acquisition.core.validation.cumulpayment;

import com.github.kristoflemmens.acquisition.core.AcquisitionRequestCommand;
import com.github.kristoflemmens.acquisition.core.AcquisitionRequestNumber;

public class ValidateCumulPayment extends AcquisitionRequestCommand {
    public ValidateCumulPayment(AcquisitionRequestNumber acquisitionRequestNumber) {
        super(acquisitionRequestNumber);
    }
}
