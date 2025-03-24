package com.personaltoy.backend.domains.integration;


import com.personaltoy.backend.domains.event.api.EventEntry;

public interface EventSender {

    void send(EventEntry event);

}
