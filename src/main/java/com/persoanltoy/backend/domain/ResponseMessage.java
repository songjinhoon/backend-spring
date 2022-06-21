package com.persoanltoy.backend.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ResponseMessage {

    ENTITY_DELETE("ENTITY_DELETE", "data delete success");

    private final String key;

    private final String value;

}
