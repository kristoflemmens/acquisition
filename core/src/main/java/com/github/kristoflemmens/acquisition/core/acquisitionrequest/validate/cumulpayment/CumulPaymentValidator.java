package com.github.kristoflemmens.acquisition.core.acquisitionrequest.validate.cumulpayment;

import com.github.kristoflemmens.acquisition.core.acquisitionrequest.validate.Validation;

import java.util.Date;

public interface CumulPaymentValidator {
    Validation validate(Date acquisitionDate, String childInss);
}
