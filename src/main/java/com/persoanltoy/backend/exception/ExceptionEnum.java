package com.persoanltoy.backend.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ExceptionEnum {

    DATA_NOT_FOUND_EXCEPTION("E00001", "data not found error"),
    NOT_VALID_REQUEST_DATA_EXCEPTION("E00002", "not valid request data error"),
    ID_DUPLICATION_EXCEPTION("E00003", "not available account error");

    private final String code;
    private final String message;

}
