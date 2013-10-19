package com.github.kristoflemmens.acquisition.core.acquisitiondate;

import com.github.kristoflemmens.acquisition.core.AcquisitionRequestEvent;
import com.github.kristoflemmens.acquisition.core.AcquisitionRequestNumber;

import java.util.Date;

public class AcquisitionDateDetermined extends AcquisitionRequestEvent {
    private final Date acquisitionDate;

    public AcquisitionDateDetermined(AcquisitionRequestNumber acquisitionRequestNumber, int version, Date acquisitionDate) {
        super(acquisitionRequestNumber, version);
        this.acquisitionDate = acquisitionDate;
    }

    public Date acquisitionDate() {
        return acquisitionDate;
    }
}
