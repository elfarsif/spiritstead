package io.github.spiritstead.entity;

import io.github.spiritstead.main.EventType;

public interface Subscriber {
    void onEventBus(EventType eventType);
}
