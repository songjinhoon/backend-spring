package com.personaltoy.backend.domains.integration.infra;

import com.personaltoy.backend.domains.event.api.EventEntry;
import com.personaltoy.backend.domains.integration.EventSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SysoutEventSender implements EventSender {

    @Override
    public void send(EventEntry event) {
        log.info("EventSender send event : " + event);
    }

}
