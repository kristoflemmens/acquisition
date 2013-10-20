package com.github.kristoflemmens.acquisition.core.acquisitionrequest.validate.legalinformation.minimal;

import com.github.kristoflemmens.acquisition.core.acquisitionrequest.validate.Validation;

import java.util.Set;

public interface MinimalLegalInformationValidator {
    Set<Validation> validate(String actorInss);
}
