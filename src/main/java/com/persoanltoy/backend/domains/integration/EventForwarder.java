package com.persoanltoy.backend.domains.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.persoanltoy.backend.domains.event.api.EventEntry;
import com.persoanltoy.backend.domains.event.api.EventStore;
import com.persoanltoy.backend.domains.order.domain.entity.event.OrderCancelEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class EventForwarder {

    private static final int DEFAULT_LIMIT_SIZE = 100;

    private final EventStore eventStore;

    private final OffsetStore offsetStore;

    private final EventSender eventSender;

    private final ObjectMapper objectMapper;

    @Scheduled(initialDelay = 1000, fixedDelay = 5000)
    public void getAndSend() {
        long nextOffset = getNextOffset();
        List<EventEntry> events = eventStore.get(nextOffset, DEFAULT_LIMIT_SIZE);
        if (!events.isEmpty()) {
            int processedCount = sendEvent(events);
            if (processedCount > 0) {
                saveNextOffset(nextOffset + processedCount);
            }
        }
    }

    private long getNextOffset() {
        return offsetStore.get();
    }

    private int sendEvent(List<EventEntry> events) {
        int processedCount = 0;
        try {
            for (EventEntry entry : events) {
                if (entry.getType().contains("OrderCancelEvent")) {
                    OrderCancelEvent orderCancelEvent = this.objectMapper.readValue(entry.getPayload(), OrderCancelEvent.class);
                    log.info("refund check ==> " + orderCancelEvent.toString());
                } else {
                    eventSender.send(entry);
                }
                processedCount++;
            }
        } catch (Exception e) {
            // 로깅 처리
            e.printStackTrace();
        }
        return processedCount;
    }

    private void saveNextOffset(long nextOffset) {
        offsetStore.update(nextOffset);
    }

}
