package com.persoanltoy.backend.domains.event.api;

import java.util.List;

public interface EventStore {

    void save(Object event);

    List<EventEntry> get(long offset, long limit);

}
