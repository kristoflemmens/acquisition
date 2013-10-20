package com.github.kristoflemmens.acquisition.core.acquisitionrequest.validate.actorduplication;

import com.github.kristoflemmens.acquisition.core.acquisitionrequest.validate.Validation;
import com.github.kristoflemmens.acquisition.core.file.Actor;

import java.util.List;
import java.util.Set;

public interface ActorDuplicationValidator {
    Set<Validation> validate(List<Actor> actors);
}
