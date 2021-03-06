package com.github.kristoflemmens.acquisition.core.acquisitionrequest.validate.itineraexistence;

import com.github.kristoflemmens.acquisition.core.acquisitionrequest.AcquisitionRequest;
import com.github.kristoflemmens.acquisition.core.acquisitionrequest.AcquisitionRequests;
import com.github.kristoflemmens.acquisition.testutils.UnitTestCase;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static com.github.kristoflemmens.acquisition.core.testutils.TestConstants.ACQUISITION_REQUEST_NUMBER;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ValidateItineraExistenceHandlerTest extends UnitTestCase {

    private final ValidateItineraExistence command = new ValidateItineraExistence(ACQUISITION_REQUEST_NUMBER);
    @InjectMocks
    private ValidateItineraExistenceHandler handler;
    @Mock
    private AcquisitionRequests acquisitionRequests;
    @Mock
    private ItineraExistenceValidator itineraExistenceValidator;
    @Mock
    private AcquisitionRequest acquisitionRequest, updatedAcquisitionRequest;

    @Test
    public void handle() throws Exception {
        when(acquisitionRequests.get(ACQUISITION_REQUEST_NUMBER)).thenReturn(acquisitionRequest);
        when(acquisitionRequest.validateItineraExistence(itineraExistenceValidator)).thenReturn(updatedAcquisitionRequest);

        handler.handle(command);

        verify(acquisitionRequests).save(updatedAcquisitionRequest);
    }
}
