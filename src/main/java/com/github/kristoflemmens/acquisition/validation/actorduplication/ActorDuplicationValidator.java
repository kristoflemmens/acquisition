package com.github.kristoflemmens.acquisition.validation.actorduplication;

import com.github.kristoflemmens.acquisition.requestDocument.Actor;
import com.github.kristoflemmens.acquisition.validation.Validation;

import java.util.List;
import java.util.Set;

public interface ActorDuplicationValidator {
    Set<Validation> validate(List<Actor> actors);
}
