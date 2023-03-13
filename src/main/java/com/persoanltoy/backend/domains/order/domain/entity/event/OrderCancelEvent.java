package com.persoanltoy.backend.domains.order.domain.entity.event;

import com.persoanltoy.backend.domains.common.event.Event;
import lombok.Getter;

@Getter
public class OrderCancelEvent extends Event {

    private final String id;

    public OrderCancelEvent(String id) {
        super();
        this.id = id;
    }

}
