package com.github.kristoflemmens.acquisition.core.testutils;

import com.github.kristoflemmens.acquisition.core.acquisitionrequest.AcquisitionRequestNumber;
import com.github.kristoflemmens.acquisition.core.acquisitionrequest.validate.Validation;
import org.joda.time.DateTime;

import java.util.Date;
import java.util.Set;

import static com.google.common.collect.Sets.newHashSet;
import static java.util.UUID.randomUUID;

public class TestConstants {
    public static final AcquisitionRequestNumber ACQUISITION_REQUEST_NUMBER = new AcquisitionRequestNumber(randomUUID());
    public static final Date ACQUISITION_DATE = new DateTime(2013, 9, 28, 0, 0).toDate();
    public static final Validation VALIDATION = new Validation();
    public static final Set<Validation> VALIDATIONS = newHashSet(VALIDATION);
    public static final String DATA = "data";
}