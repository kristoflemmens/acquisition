package com.github.kristoflemmens.acquisition.core.acquisitionrequest.validate.legalinformation.minimal;

import com.github.kristoflemmens.acquisition.core.acquisitionrequest.AcquisitionRequest;
import com.github.kristoflemmens.acquisition.core.acquisitionrequest.AcquisitionRequests;
import com.github.kristoflemmens.acquisition.messaging.CommandHandler;
import com.google.common.eventbus.Subscribe;

public class ValidateMinimalLegalInformationHandler implements CommandHandler<ValidateMinimalLegalInformation> {
    private final AcquisitionRequests acquisitionRequests;
    private final MinimalLegalInformationValidator minimalLegalInformationValidator;

    public ValidateMinimalLegalInformationHandler(AcquisitionRequests acquisitionRequests, MinimalLegalInformationValidator minimalLegalInformationValidator) {
        this.acquisitionRequests = acquisitionRequests;
        this.minimalLegalInformationValidator = minimalLegalInformationValidator;
    }

    @Override
    @Subscribe
    public void handle(ValidateMinimalLegalInformation command) {
        AcquisitionRequest original = acquisitionRequests.get(command.acquisitionRequestNumber());
        AcquisitionRequest updated = original.validateMinimalLegalInformationOfActors(minimalLegalInformationValidator);
        acquisitionRequests.save(updated);
    }
}
