package com.github.kristoflemmens.messaging;

import com.google.common.eventbus.DeadEvent;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

public class InMemoryCommandSender implements CommandSender {
    private final EventBus eventBus;

    private InMemoryCommandSender(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    public static InMemoryCommandSender inMemoryCommandSender() {
        return new InMemoryCommandSender(eventBus());
    }

    private static EventBus eventBus() {
        EventBus eventBus = new EventBus("commands");
        eventBus.register(new DeadEventHandler());
        return eventBus;
    }


    @Override
    public void send(Command command) {
        eventBus.post(command);
    }

    @Override
    public void register(CommandHandler<?> handler) {
        eventBus.register(handler);
    }

    private static class DeadEventHandler {
        @Subscribe
        public void handle(DeadEvent deadEvent) {
            throw new UnsupportedOperationException("No handler defined for command " + deadEvent.getEvent().toString());
        }
    }
}
