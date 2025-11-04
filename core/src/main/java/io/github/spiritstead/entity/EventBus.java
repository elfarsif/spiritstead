package io.github.spiritstead.entity;

import io.github.spiritstead.main.EventType;
import io.github.spiritstead.main.Game;
import io.github.spiritstead.object.GameObject;

import java.util.*;

public final class EventBus {
    private final EnumMap<EventType, List<Subscriber>> eventTypeSubscribers = new EnumMap<>(EventType.class);

    public EventBus(EventType... eventTypes) {
        for (EventType eventType : eventTypes) {
            this.eventTypeSubscribers.put(eventType, new ArrayList<>());
        }
    }

    public void subscribe(EventType eventType, Subscriber subscriber) {
        List<Subscriber> subscribers = this.eventTypeSubscribers.get(eventType);
        subscribers.add(subscriber);
    }

    public void publish(EventType eventType, GameObject gameObject) {
        List<Subscriber> subscribers = this.eventTypeSubscribers.get(eventType);
        for (Subscriber subscriber : subscribers) {
            subscriber.onEventBus(eventType, gameObject);
        }
    }

}
