package com.github.kristoflemmens.acquisition.core.validation.cumulpayment;

import com.github.kristoflemmens.acquisition.core.AcquisitionRequests;
import com.github.kristoflemmens.acquisition.messaging.CommandHandler;
import com.google.common.eventbus.Subscribe;

public class ValidateCumulPaymentHandler implements CommandHandler<ValidateCumulPayment> {
    private final AcquisitionRequests acquisitionRequests;
    private final CumulPaymentValidator cumulPaymentValidator;

    public ValidateCumulPaymentHandler(AcquisitionRequests acquisitionRequests, CumulPaymentValidator cumulPaymentValidator) {
        this.acquisitionRequests = acquisitionRequests;
        this.cumulPaymentValidator = cumulPaymentValidator;
    }

    @Override
    @Subscribe
    public void handle(ValidateCumulPayment command) {
        acquisitionRequests.save(acquisitionRequests.get(command.acquisitionRequestNumber()).validateCumulPaymentForChildren(cumulPaymentValidator));
    }

}
