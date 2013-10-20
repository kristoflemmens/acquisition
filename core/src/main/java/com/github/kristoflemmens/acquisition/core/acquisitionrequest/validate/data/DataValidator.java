package com.github.kristoflemmens.acquisition.core.acquisitionrequest.validate.data;

import com.github.kristoflemmens.acquisition.core.acquisitionrequest.validate.Validation;

import java.util.Set;

public interface DataValidator {

    Set<Validation> validate(String data);
}
