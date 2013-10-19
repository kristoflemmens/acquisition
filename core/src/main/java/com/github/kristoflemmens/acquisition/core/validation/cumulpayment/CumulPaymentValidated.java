package com.github.kristoflemmens.acquisition.core.validation.cumulpayment;

import com.github.kristoflemmens.acquisition.core.AcquisitionRequestEvent;
import com.github.kristoflemmens.acquisition.core.AcquisitionRequestNumber;
import com.github.kristoflemmens.acquisition.core.validation.Validation;

public class CumulPaymentValidated extends AcquisitionRequestEvent {
    private final Validation cumulPaymentsValidation;

    public CumulPaymentValidated(AcquisitionRequestNumber acquisitionRequestNumber, int version, Validation cumulPaymentsValidation) {
        super(acquisitionRequestNumber, version);
        this.cumulPaymentsValidation = cumulPaymentsValidation;
    }
}
