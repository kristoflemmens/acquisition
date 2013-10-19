package com.github.kristoflemmens.acquisition.core.validation.actorduplication;

import com.github.kristoflemmens.acquisition.core.requestDocument.Actor;
import com.github.kristoflemmens.acquisition.core.validation.Validation;

import java.util.List;
import java.util.Set;

public interface ActorDuplicationValidator {
    Set<Validation> validate(List<Actor> actors);
}
