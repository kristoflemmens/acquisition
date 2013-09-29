package com.github.kristoflemmens.acquisition.validation.data;

import com.github.kristoflemmens.acquisition.AcquisitionRequests;
import com.github.kristoflemmens.messaging.CommandHandler;
import com.google.common.eventbus.Subscribe;

public class ValidateDataHandler implements CommandHandler<ValidateData> {

    private final AcquisitionRequests acquisitionRequests;
    private final DataValidator dataValidator;

    public ValidateDataHandler(AcquisitionRequests acquisitionRequests, DataValidator dataValidator) {
        this.acquisitionRequests = acquisitionRequests;
        this.dataValidator = dataValidator;
    }

    @Override
    @Subscribe
    public void handle(ValidateData command) {
        acquisitionRequests.save(acquisitionRequests.get(command.acquisitionRequestNumber()).validateData(dataValidator));
    }

}
