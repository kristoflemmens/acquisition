package com.github.kristoflemmens.acquisition.core.acquisitionrequest.create;

import com.github.kristoflemmens.acquisition.core.acquisitionrequest.AcquisitionRequestEvent;
import com.github.kristoflemmens.acquisition.core.acquisitionrequest.AcquisitionRequestNumber;

public class AcquisitionRequestCreated extends AcquisitionRequestEvent {

    private final String data;

    public AcquisitionRequestCreated(AcquisitionRequestNumber acquisitionRequestNumber, int version, String data) {
        super(acquisitionRequestNumber, version);
        this.data = data;
    }

    public String data() {
        return data;
    }
}
