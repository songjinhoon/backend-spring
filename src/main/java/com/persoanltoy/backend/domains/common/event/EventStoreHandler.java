package com.persoanltoy.backend.domains.common.event;

import com.persoanltoy.backend.domains.event.api.EventStore;
import com.persoanltoy.backend.domains.order.domain.entity.event.OrderCancelEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Slf4j
@RequiredArgsConstructor
@Component
public class EventStoreHandler {

    private final EventStore eventStore;

    @TransactionalEventListener(classes = OrderCancelEvent.class, phase = TransactionPhase.AFTER_COMMIT)
    public void handle(OrderCancelEvent event) {
        log.info("event save :: " + event.toString());
        eventStore.save(event);
    }

}
