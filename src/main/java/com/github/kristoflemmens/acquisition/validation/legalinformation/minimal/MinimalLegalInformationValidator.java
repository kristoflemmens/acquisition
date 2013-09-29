package com.github.kristoflemmens.acquisition.validation.legalinformation.minimal;

import com.github.kristoflemmens.acquisition.validation.Validation;

import java.util.Set;

public interface MinimalLegalInformationValidator {
    Set<Validation> validate(String actorInss);
}
