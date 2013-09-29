package com.github.kristoflemmens.acquisition.start;

import com.github.kristoflemmens.acquisition.AcquisitionRequest;
import com.github.kristoflemmens.acquisition.AcquisitionRequestFactory;
import com.github.kristoflemmens.acquisition.AcquisitionRequests;
import com.github.kristoflemmens.testutils.UnitTestCase;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static com.github.kristoflemmens.acquisition.TestConstants.ACQUISITION_REQUEST_NUMBER;
import static com.github.kristoflemmens.acquisition.TestConstants.DATA;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CreateAcquisitionRequestHandlerTest extends UnitTestCase {

    private final CreateAcquisitionRequest command = new CreateAcquisitionRequest(ACQUISITION_REQUEST_NUMBER, DATA);
    @InjectMocks
    private CreateAcquisitionRequestHandler handler;
    @Mock
    private AcquisitionRequests acquisitionRequests;
    @Mock
    private AcquisitionRequestFactory acquisitionRequestFactory;
    @Mock
    private AcquisitionRequest acquisitionRequest;

    @Test
    public void handle() throws Exception {
        when(acquisitionRequestFactory.create(ACQUISITION_REQUEST_NUMBER, DATA)).thenReturn(acquisitionRequest);

        handler.handle(command);

        verify(acquisitionRequests).save(acquisitionRequest);
    }
}
