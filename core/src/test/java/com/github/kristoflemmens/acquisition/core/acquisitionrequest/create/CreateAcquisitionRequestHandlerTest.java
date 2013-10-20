package com.github.kristoflemmens.acquisition.core.acquisitionrequest.create;

import com.github.kristoflemmens.acquisition.core.acquisitionrequest.AcquisitionRequest;
import com.github.kristoflemmens.acquisition.core.acquisitionrequest.AcquisitionRequestFactory;
import com.github.kristoflemmens.acquisition.core.acquisitionrequest.AcquisitionRequests;
import com.github.kristoflemmens.acquisition.testutils.UnitTestCase;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static com.github.kristoflemmens.acquisition.core.testutils.TestConstants.ACQUISITION_REQUEST_NUMBER;
import static com.github.kristoflemmens.acquisition.core.testutils.TestConstants.DATA;
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
