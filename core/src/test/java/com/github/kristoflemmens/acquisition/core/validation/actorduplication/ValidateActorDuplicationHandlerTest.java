package com.github.kristoflemmens.acquisition.core.validation.actorduplication;

import com.github.kristoflemmens.acquisition.core.AcquisitionRequest;
import com.github.kristoflemmens.acquisition.core.AcquisitionRequests;
import com.github.kristoflemmens.acquisition.testutils.UnitTestCase;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static com.github.kristoflemmens.acquisition.core.TestConstants.ACQUISITION_REQUEST_NUMBER;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ValidateActorDuplicationHandlerTest extends UnitTestCase {

    private final ValidateActorDuplication command = new ValidateActorDuplication(ACQUISITION_REQUEST_NUMBER);
    @InjectMocks
    private ValidateActorDuplicationHandler handler;
    @Mock
    private AcquisitionRequests acquisitionRequests;
    @Mock
    private ActorDuplicationValidator actorDuplicationValidator;
    @Mock
    private AcquisitionRequest acquisitionRequest, updatedAcquisitionRequest;

    @Test
    public void handle() throws Exception {
        when(acquisitionRequests.get(ACQUISITION_REQUEST_NUMBER)).thenReturn(acquisitionRequest);
        when(acquisitionRequest.validateActorDuplication(actorDuplicationValidator)).thenReturn(updatedAcquisitionRequest);

        handler.handle(command);

        verify(acquisitionRequests).save(updatedAcquisitionRequest);
    }
}
