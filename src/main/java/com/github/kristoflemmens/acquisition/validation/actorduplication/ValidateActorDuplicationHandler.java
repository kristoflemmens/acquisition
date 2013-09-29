package com.github.kristoflemmens.acquisition.validation.actorduplication;

import com.github.kristoflemmens.acquisition.AcquisitionRequests;
import com.github.kristoflemmens.messaging.CommandHandler;
import com.google.common.eventbus.Subscribe;

public class ValidateActorDuplicationHandler implements CommandHandler<ValidateActorDuplication> {
    private final AcquisitionRequests acquisitionRequests;
    private final ActorDuplicationValidator actorDuplicationValidator;

    public ValidateActorDuplicationHandler(AcquisitionRequests acquisitionRequests, ActorDuplicationValidator actorDuplicationValidator) {
        this.acquisitionRequests = acquisitionRequests;
        this.actorDuplicationValidator = actorDuplicationValidator;
    }

    @Override
    @Subscribe
    public void handle(ValidateActorDuplication command) {
        acquisitionRequests.save(acquisitionRequests.get(command.acquisitionRequestNumber()).validateActorDuplication(actorDuplicationValidator));
    }
}
