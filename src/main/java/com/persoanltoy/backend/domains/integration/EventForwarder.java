package com.persoanltoy.backend.domains.integration;

import com.persoanltoy.backend.domains.event.api.EventEntry;
import com.persoanltoy.backend.domains.event.api.EventStore;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class EventForwarder {

    private static final int DEFAULT_LIMIT_SIZE = 100;

    private final EventStore eventStore;

    private final OffsetStore offsetStore;

    private final EventSender eventSender;

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
                eventSender.send(entry);
                processedCount++;
            }
        } catch (Exception ex) {
            // 로깅 처리
        }
        return processedCount;
    }

    private void saveNextOffset(long nextOffset) {
        offsetStore.update(nextOffset);
    }

}
