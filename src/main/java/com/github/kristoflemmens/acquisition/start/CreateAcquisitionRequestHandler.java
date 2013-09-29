package com.github.kristoflemmens.acquisition.start;

import com.github.kristoflemmens.acquisition.AcquisitionRequestFactory;
import com.github.kristoflemmens.acquisition.AcquisitionRequests;
import com.github.kristoflemmens.messaging.CommandHandler;
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
