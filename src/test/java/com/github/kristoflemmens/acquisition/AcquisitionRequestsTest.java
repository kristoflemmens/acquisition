package com.github.kristoflemmens.acquisition;

import com.github.kristoflemmens.eventsourcing.Event;
import com.github.kristoflemmens.eventsourcing.EventStore;
import com.github.kristoflemmens.testutils.UnitTestCase;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;

import static com.github.kristoflemmens.acquisition.TestConstants.ACQUISITION_REQUEST_NUMBER;
import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AcquisitionRequestsTest extends UnitTestCase {

    @InjectMocks
    private AcquisitionRequests acquisitionRequests;
    @Mock
    private EventStore eventStore;
    @Mock
    private AcquisitionRequestFactory acquisitionRequestFactory;
    @Mock
    private AcquisitionRequest acquisitionRequest;
    @Mock
    private List<Event> history;

    @Test
    public void save() throws Exception {
        acquisitionRequests.save(acquisitionRequest);

        verify(eventStore).save(acquisitionRequest);
    }

    @Test
    public void get() throws Exception {
        when(eventStore.eventsFor(ACQUISITION_REQUEST_NUMBER)).thenReturn(history);
        when(acquisitionRequestFactory.loadFromHistory(history)).thenReturn(acquisitionRequest);

        assertThat(acquisitionRequests.get(ACQUISITION_REQUEST_NUMBER)).isEqualTo(acquisitionRequest);
    }
}
