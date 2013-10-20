package com.github.kristoflemmens.acquisition.core.acquisitionrequest.validate.cumulpayment;

import com.github.kristoflemmens.acquisition.core.acquisitionrequest.AcquisitionRequest;
import com.github.kristoflemmens.acquisition.core.acquisitionrequest.AcquisitionRequests;
import com.github.kristoflemmens.acquisition.testutils.UnitTestCase;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static com.github.kristoflemmens.acquisition.core.testutils.TestConstants.ACQUISITION_REQUEST_NUMBER;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ValidateCumulPaymentHandlerTest extends UnitTestCase {

    private final ValidateCumulPayment command = new ValidateCumulPayment(ACQUISITION_REQUEST_NUMBER);
    @InjectMocks
    private ValidateCumulPaymentHandler handler;
    @Mock
    private AcquisitionRequests acquisitionRequests;
    @Mock
    private CumulPaymentValidator cumulPaymentValidator;
    @Mock
    private AcquisitionRequest acquisitionRequest, updatedAcquisitionRequest;

    @Test
    public void handle() throws Exception {
        when(acquisitionRequests.get(ACQUISITION_REQUEST_NUMBER)).thenReturn(acquisitionRequest);
        when(acquisitionRequest.validateCumulPaymentForChildren(cumulPaymentValidator)).thenReturn(updatedAcquisitionRequest);

        handler.handle(command);

        verify(acquisitionRequests).save(updatedAcquisitionRequest);
    }
}
