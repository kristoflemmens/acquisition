package com.github.kristoflemmens.acquisition.core.validation.data;

import com.github.kristoflemmens.acquisition.core.validation.Validation;

import java.util.Set;

public interface DataValidator {

    Set<Validation> validate(String data);
}
