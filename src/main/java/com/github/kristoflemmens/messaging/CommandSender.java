package com.github.kristoflemmens.messaging;

public interface CommandSender {
    void send(Command command);

    void register(CommandHandler<?> handler);
}
