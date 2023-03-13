package com.persoanltoy.backend.domains.common.event;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public abstract class Event {

    private final LocalDateTime eventTime;

    public Event() {
        this.eventTime = LocalDateTime.now();
    }

}
