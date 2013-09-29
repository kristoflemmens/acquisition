package com.github.kristoflemmens.messaging;

public interface CommandHandler<COMMAND extends Command> {
    void handle(COMMAND command);
}
