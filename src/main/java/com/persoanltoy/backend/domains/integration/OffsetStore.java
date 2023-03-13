package com.persoanltoy.backend.domains.integration;

public interface OffsetStore {

    long get();

    void update(long nextOffset);

}
