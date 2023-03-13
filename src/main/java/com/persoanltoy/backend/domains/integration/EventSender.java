package com.persoanltoy.backend.domains.integration;


import com.persoanltoy.backend.domains.event.api.EventEntry;

public interface EventSender {

    void send(EventEntry event);

}
