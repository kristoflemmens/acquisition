package com.github.kristoflemmens.acquisition.core.acquisitionrequest.validate.legalinformation.minimal;

import com.github.kristoflemmens.acquisition.core.acquisitionrequest.AcquisitionRequest;
import com.github.kristoflemmens.acquisition.core.acquisitionrequest.AcquisitionRequests;
import com.github.kristoflemmens.acquisition.testutils.UnitTestCase;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static com.github.kristoflemmens.acquisition.core.testutils.TestConstants.ACQUISITION_REQUEST_NUMBER;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ValidateMinimalLegalInformationHandlerTest extends UnitTestCase {

    private final ValidateMinimalLegalInformation command = new ValidateMinimalLegalInformation(ACQUISITION_REQUEST_NUMBER);
    @InjectMocks
    private ValidateMinimalLegalInformationHandler handler;
    @Mock
    private AcquisitionRequests acquisitionRequests;
    @Mock
    private MinimalLegalInformationValidator minimalLegalInformationValidator;
    @Mock
    private AcquisitionRequest acquisitionRequest, updatedAcquisitionRequest;

    @Test
    public void handle() throws Exception {
        when(acquisitionRequests.get(ACQUISITION_REQUEST_NUMBER)).thenReturn(acquisitionRequest);
        when(acquisitionRequest.validateMinimalLegalInformationOfActors(minimalLegalInformationValidator)).thenReturn(updatedAcquisitionRequest);

        handler.handle(command);

        verify(acquisitionRequests).save(updatedAcquisitionRequest);
    }
}
