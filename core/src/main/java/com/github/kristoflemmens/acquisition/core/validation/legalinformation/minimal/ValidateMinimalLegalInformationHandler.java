package com.github.kristoflemmens.acquisition.core.validation.legalinformation.minimal;

import com.github.kristoflemmens.acquisition.core.AcquisitionRequests;
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
        acquisitionRequests.save(acquisitionRequests.get(command.acquisitionRequestNumber()).validateMinimalLegalInformationOfActors(minimalLegalInformationValidator));
    }
}
