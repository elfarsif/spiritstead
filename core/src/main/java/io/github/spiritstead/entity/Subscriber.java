package io.github.spiritstead.entity;

import io.github.spiritstead.main.EventType;
import io.github.spiritstead.object.GameObject;

public interface Subscriber {
    void onEventBus(EventType eventType, GameObject gameObject);
}
