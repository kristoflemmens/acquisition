package com.github.kristoflemmens.acquisition.start;

import com.github.kristoflemmens.acquisition.AcquisitionRequestCommand;
import com.github.kristoflemmens.acquisition.AcquisitionRequestNumber;

public class CreateAcquisitionRequest extends AcquisitionRequestCommand {

    private final String data;

    public CreateAcquisitionRequest(AcquisitionRequestNumber acquisitionRequestNumber, String data) {
        super(acquisitionRequestNumber);
        this.data = data;
    }

    public String data() {
        return data;
    }
}
