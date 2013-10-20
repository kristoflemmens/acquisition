package com.github.kristoflemmens.acquisition.core.acquisitionrequest.validate.data;

import com.github.kristoflemmens.acquisition.core.acquisitionrequest.AcquisitionRequests;
import com.github.kristoflemmens.acquisition.messaging.CommandHandler;
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
