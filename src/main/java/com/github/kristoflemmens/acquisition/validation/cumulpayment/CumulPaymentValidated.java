package com.github.kristoflemmens.acquisition.validation.cumulpayment;

import com.github.kristoflemmens.acquisition.AcquisitionRequestEvent;
import com.github.kristoflemmens.acquisition.AcquisitionRequestNumber;
import com.github.kristoflemmens.acquisition.validation.Validation;

public class CumulPaymentValidated extends AcquisitionRequestEvent {
    private final Validation cumulPaymentsValidation;

    public CumulPaymentValidated(AcquisitionRequestNumber acquisitionRequestNumber, int version, Validation cumulPaymentsValidation) {
        super(acquisitionRequestNumber, version);
        this.cumulPaymentsValidation = cumulPaymentsValidation;
    }
}
