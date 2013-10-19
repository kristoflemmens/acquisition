package com.github.kristoflemmens.acquisition.core.validation.cumulpayment;

import com.github.kristoflemmens.acquisition.core.validation.Validation;

import java.util.Date;

public interface CumulPaymentValidator {
    Validation validate(Date acquisitionDate, String childInss);
}
