package com.github.kristoflemmens.acquisition.core;

import com.github.kristoflemmens.acquisition.core.start.AcquisitionRequestCreated;
import com.github.kristoflemmens.acquisition.eventsourcing.Event;
import com.github.kristoflemmens.acquisition.testutils.UnitTestCase;
import com.google.common.collect.Lists;
import org.junit.Test;
import org.mockito.InjectMocks;

import static org.fest.assertions.api.Assertions.assertThat;

public class AcquisitionRequestFactoryTest extends UnitTestCase {

    private final AcquisitionRequestCreated acquisitionRequestCreated = new AcquisitionRequestCreated(TestConstants.ACQUISITION_REQUEST_NUMBER, 0, TestConstants.DATA);
    @InjectMocks
    private AcquisitionRequestFactory factory;

    @Test
    public void create() throws Exception {
        AcquisitionRequest actual = factory.create(TestConstants.ACQUISITION_REQUEST_NUMBER, TestConstants.DATA);

        assertThat(actual.id()).isEqualTo(TestConstants.ACQUISITION_REQUEST_NUMBER);
        assertThat(actual.version()).isEqualTo(-1);
        assertThat(actual.unsavedEvents()).containsExactly(acquisitionRequestCreated);
    }

    @Test
    public void loadFromHistory() throws Exception {
        AcquisitionRequest actual = factory.loadFromHistory(Lists.<Event>newArrayList(acquisitionRequestCreated));

        assertThat(actual.id()).isEqualTo(TestConstants.ACQUISITION_REQUEST_NUMBER);
        assertThat(actual.version()).isEqualTo(0);
        assertThat(actual.unsavedEvents()).isEmpty();
    }
}
