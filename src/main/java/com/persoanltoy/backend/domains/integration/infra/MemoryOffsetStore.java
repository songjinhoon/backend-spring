package com.persoanltoy.backend.domains.integration.infra;

import com.persoanltoy.backend.domains.integration.OffsetStore;
import org.springframework.stereotype.Component;

@Component
public class MemoryOffsetStore implements OffsetStore {

    private long nextOffset = 0;

    @Override
    public long get() {
        return nextOffset;
    }

    @Override
    public void update(long nextOffset) {
        this.nextOffset = nextOffset;
    }

}
