package com.github.kristoflemmens.acquisition.start;

import com.github.kristoflemmens.acquisition.AcquisitionRequestEvent;
import com.github.kristoflemmens.acquisition.AcquisitionRequestNumber;

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
