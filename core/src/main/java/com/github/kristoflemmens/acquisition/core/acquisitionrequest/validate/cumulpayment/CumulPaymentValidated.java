package com.github.kristoflemmens.acquisition.core.acquisitionrequest.validate.cumulpayment;

import com.github.kristoflemmens.acquisition.core.acquisitionrequest.AcquisitionRequestEvent;
import com.github.kristoflemmens.acquisition.core.acquisitionrequest.AcquisitionRequestNumber;
import com.github.kristoflemmens.acquisition.core.acquisitionrequest.validate.Validation;

public class CumulPaymentValidated extends AcquisitionRequestEvent {
    private final Validation cumulPaymentsValidation;

    public CumulPaymentValidated(AcquisitionRequestNumber acquisitionRequestNumber, int version, Validation cumulPaymentsValidation) {
        super(acquisitionRequestNumber, version);
        this.cumulPaymentsValidation = cumulPaymentsValidation;
    }
}
