package com.github.kristoflemmens.acquisition.core.acquisitionrequest.acquisitiondate;

import com.github.kristoflemmens.acquisition.core.acquisitionrequest.AcquisitionRequestCommand;
import com.github.kristoflemmens.acquisition.core.acquisitionrequest.AcquisitionRequestNumber;

public class DetermineAcquisitionDate extends AcquisitionRequestCommand {
    public DetermineAcquisitionDate(AcquisitionRequestNumber acquisitionRequestNumber) {
        super(acquisitionRequestNumber);
    }
}
