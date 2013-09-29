package com.github.kristoflemmens.acquisition.acquisitiondate;

import com.github.kristoflemmens.acquisition.AcquisitionRequests;
import com.github.kristoflemmens.messaging.CommandHandler;
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
