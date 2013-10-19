package com.github.kristoflemmens.acquisition.core.acquisitiondate;

import com.github.kristoflemmens.acquisition.core.AcquisitionRequestCommand;
import com.github.kristoflemmens.acquisition.core.AcquisitionRequestNumber;

public class DetermineAcquisitionDate extends AcquisitionRequestCommand {
    public DetermineAcquisitionDate(AcquisitionRequestNumber acquisitionRequestNumber) {
        super(acquisitionRequestNumber);
    }
}
