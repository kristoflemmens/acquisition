package com.github.kristoflemmens.acquisition.core.acquisitiondate;

import com.github.kristoflemmens.acquisition.core.AcquisitionRequests;
import com.github.kristoflemmens.acquisition.messaging.CommandHandler;
import com.google.common.eventbus.Subscribe;

public class DetermineAcquisitionDateHandler implements CommandHandler<DetermineAcquisitionDate> {

    private final AcquisitionRequests acquisitionRequests;
    private final AcquisitionDates acquisitionDates;

    public DetermineAcquisitionDateHandler(AcquisitionRequests acquisitionRequests, AcquisitionDates acquisitionDates) {
        this.acquisitionRequests = acquisitionRequests;
        this.acquisitionDates = acquisitionDates;
    }

    @Override
    @Subscribe
    public void handle(DetermineAcquisitionDate command) {
        acquisitionRequests.save(acquisitionRequests.get(command.acquisitionRequestNumber()).determineAcquisitionDate(acquisitionDates));
    }

}
