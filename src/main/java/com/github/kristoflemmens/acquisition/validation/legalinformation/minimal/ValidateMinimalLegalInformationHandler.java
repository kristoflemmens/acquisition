package com.github.kristoflemmens.acquisition.validation.legalinformation.minimal;

import com.github.kristoflemmens.acquisition.AcquisitionRequests;
import com.github.kristoflemmens.messaging.CommandHandler;
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
        acquisitionRequests.save(acquisitionRequests.get(command.acquisitionRequestNumber()).validateMinimalLegalInformationOfActors(minimalLegalInformationValidator));
    }
}
