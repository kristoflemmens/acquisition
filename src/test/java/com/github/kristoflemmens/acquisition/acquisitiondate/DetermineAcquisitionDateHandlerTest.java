package com.github.kristoflemmens.acquisition.acquisitiondate;

import com.github.kristoflemmens.acquisition.AcquisitionRequest;
import com.github.kristoflemmens.acquisition.AcquisitionRequests;
import com.github.kristoflemmens.testutils.UnitTestCase;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static com.github.kristoflemmens.acquisition.TestConstants.ACQUISITION_REQUEST_NUMBER;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class DetermineAcquisitionDateHandlerTest extends UnitTestCase {

    private final DetermineAcquisitionDate command = new DetermineAcquisitionDate(ACQUISITION_REQUEST_NUMBER);
    @InjectMocks
    private DetermineAcquisitionDateHandler handler;
    @Mock
    private AcquisitionDates acquisitionDates;
    @Mock
    private AcquisitionRequests acquisitionRequests;
    @Mock
    private AcquisitionRequest acquisitionRequest, updatedAcquisitionRequest;

    @Test
    public void handle() throws Exception {
        when(acquisitionRequests.get(ACQUISITION_REQUEST_NUMBER)).thenReturn(acquisitionRequest);
        when(acquisitionRequest.determineAcquisitionDate(acquisitionDates)).thenReturn(updatedAcquisitionRequest);

        handler.handle(command);

        verify(acquisitionRequests).save(updatedAcquisitionRequest);
    }
}
