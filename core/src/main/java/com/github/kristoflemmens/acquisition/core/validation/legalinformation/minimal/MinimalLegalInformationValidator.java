package com.github.kristoflemmens.acquisition.core.validation.legalinformation.minimal;

import com.github.kristoflemmens.acquisition.core.validation.Validation;

import java.util.Set;

public interface MinimalLegalInformationValidator {
    Set<Validation> validate(String actorInss);
}
