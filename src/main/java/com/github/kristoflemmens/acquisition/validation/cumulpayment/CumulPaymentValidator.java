package com.github.kristoflemmens.acquisition.validation.cumulpayment;

import com.github.kristoflemmens.acquisition.validation.Validation;

import java.util.Date;

public interface CumulPaymentValidator {
    Validation validate(Date acquisitionDate, String childInss);
}
