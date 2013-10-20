package com.github.kristoflemmens.acquisition.core.acquisitionrequest.validate.cumulpayment;

import com.github.kristoflemmens.acquisition.core.acquisitionrequest.AcquisitionRequestCommand;
import com.github.kristoflemmens.acquisition.core.acquisitionrequest.AcquisitionRequestNumber;

public class ValidateCumulPayment extends AcquisitionRequestCommand {
    public ValidateCumulPayment(AcquisitionRequestNumber acquisitionRequestNumber) {
        super(acquisitionRequestNumber);
    }
}
