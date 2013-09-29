package com.github.kristoflemmens.acquisition.acquisitiondate;

import com.github.kristoflemmens.acquisition.AcquisitionRequestCommand;
import com.github.kristoflemmens.acquisition.AcquisitionRequestNumber;

public class DetermineAcquisitionDate extends AcquisitionRequestCommand {
    public DetermineAcquisitionDate(AcquisitionRequestNumber acquisitionRequestNumber) {
        super(acquisitionRequestNumber);
    }
}
