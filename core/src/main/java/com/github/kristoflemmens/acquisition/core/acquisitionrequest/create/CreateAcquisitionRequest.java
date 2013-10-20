package com.github.kristoflemmens.acquisition.core.acquisitionrequest.create;

import com.github.kristoflemmens.acquisition.core.acquisitionrequest.AcquisitionRequestCommand;
import com.github.kristoflemmens.acquisition.core.acquisitionrequest.AcquisitionRequestNumber;

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
