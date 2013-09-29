package com.github.kristoflemmens.acquisition.validation.itineraexistence;

import com.github.kristoflemmens.acquisition.AcquisitionRequests;
import com.github.kristoflemmens.messaging.CommandHandler;
import com.google.common.eventbus.Subscribe;

public class ValidateItineraExistenceHandler implements CommandHandler<ValidateItineraExistence> {
    private final AcquisitionRequests acquisitionRequests;
    private final ItineraExistenceValidator itineraExistenceValidator;

    public ValidateItineraExistenceHandler(AcquisitionRequests acquisitionRequests, ItineraExistenceValidator itineraExistenceValidator) {
        this.acquisitionRequests = acquisitionRequests;
        this.itineraExistenceValidator = itineraExistenceValidator;
    }

    @Override
    @Subscribe
    public void handle(ValidateItineraExistence command) {
        acquisitionRequests.save(acquisitionRequests.get(command.acquisitionRequestNumber()).validateItineraExistence(itineraExistenceValidator));
    }
}
