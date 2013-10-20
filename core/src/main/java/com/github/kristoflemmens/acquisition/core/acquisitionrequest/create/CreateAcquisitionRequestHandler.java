package com.github.kristoflemmens.acquisition.core.acquisitionrequest.create;

import com.github.kristoflemmens.acquisition.core.acquisitionrequest.AcquisitionRequestFactory;
import com.github.kristoflemmens.acquisition.core.acquisitionrequest.AcquisitionRequests;
import com.github.kristoflemmens.acquisition.messaging.CommandHandler;
import com.google.common.eventbus.Subscribe;

public class CreateAcquisitionRequestHandler implements CommandHandler<CreateAcquisitionRequest> {

    private final AcquisitionRequests acquisitionRequests;
    private final AcquisitionRequestFactory acquisitionRequestFactory;

    public CreateAcquisitionRequestHandler(AcquisitionRequests acquisitionRequests, AcquisitionRequestFactory acquisitionRequestFactory) {
        this.acquisitionRequests = acquisitionRequests;
        this.acquisitionRequestFactory = acquisitionRequestFactory;
    }

    @Override
    @Subscribe
    public void handle(CreateAcquisitionRequest command) {
        acquisitionRequests.save(acquisitionRequestFactory.create(command.acquisitionRequestNumber(), command.data()));
    }
}
