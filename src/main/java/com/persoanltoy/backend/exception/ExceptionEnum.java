package com.persoanltoy.backend.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ExceptionEnum {

    DATA_NOT_FOUND_EXCEPTION("E00001", "data not found error"),
    BAD_REQUEST_EXCEPTION("E00002", "bad request error");

    private final String code;
    private final String message;

}
