package com.github.kristoflemmens.acquisition.messaging;

public interface CommandSender {
    void send(Command command);

    void register(CommandHandler<?> handler);
}
