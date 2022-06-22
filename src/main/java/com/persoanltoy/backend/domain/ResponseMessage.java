package com.persoanltoy.backend.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ResponseMessage {

    ENTITY_DELETE("ENTITY_DELETE", "data delete success"),
    ACCOUNT_DUPLICATION("ACCOUNT_DUPLICATION", "this account available");

    private final String key;

    private final String value;

}
