package com.github.kristoflemmens.acquisition.messaging;

public interface CommandHandler<COMMAND extends Command> {
    void handle(COMMAND command);
}
