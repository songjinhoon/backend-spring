package com.personaltoy.backend.domains.integration;

public interface OffsetStore {

    long get();

    void update(long nextOffset);

}
