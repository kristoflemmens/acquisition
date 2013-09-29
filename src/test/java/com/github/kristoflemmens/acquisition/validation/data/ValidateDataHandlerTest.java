package com.github.kristoflemmens.acquisition.validation.data;

import com.github.kristoflemmens.acquisition.AcquisitionRequest;
import com.github.kristoflemmens.acquisition.AcquisitionRequests;
import com.github.kristoflemmens.testutils.UnitTestCase;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static com.github.kristoflemmens.acquisition.TestConstants.ACQUISITION_REQUEST_NUMBER;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ValidateDataHandlerTest extends UnitTestCase {

    private final ValidateData command = new ValidateData(ACQUISITION_REQUEST_NUMBER);
    @InjectMocks
    private ValidateDataHandler handler;
    @Mock
    private AcquisitionRequests acquisitionRequests;
    @Mock
    private DataValidator dataValidator;
    @Mock
    private AcquisitionRequest acquisitionRequest, updatedAcquisitionRequest;

    @Test
    public void handle() throws Exception {
        when(acquisitionRequests.get(ACQUISITION_REQUEST_NUMBER)).thenReturn(acquisitionRequest);
        when(acquisitionRequest.validateData(dataValidator)).thenReturn(updatedAcquisitionRequest);

        handler.handle(command);

        verify(acquisitionRequests).save(updatedAcquisitionRequest);
    }
}
