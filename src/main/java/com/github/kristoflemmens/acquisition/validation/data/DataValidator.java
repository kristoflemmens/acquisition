package com.github.kristoflemmens.acquisition.validation.data;

import com.github.kristoflemmens.acquisition.validation.Validation;

import java.util.Set;

public interface DataValidator {

    Set<Validation> validate(String data);
}
