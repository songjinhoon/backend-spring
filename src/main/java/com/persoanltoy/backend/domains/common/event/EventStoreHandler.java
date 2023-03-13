package com.persoanltoy.backend.domains.common.event;

import com.persoanltoy.backend.domains.event.api.EventStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class EventStoreHandler {

    private final EventStore eventStore;

    @EventListener(Event.class)
    public void handle(Event event) {
        log.info("event save :: " + event.toString());
        eventStore.save(event);
    }

}
