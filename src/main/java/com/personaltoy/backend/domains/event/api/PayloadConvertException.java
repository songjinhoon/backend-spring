package com.personaltoy.backend.domains.event.api;

public class PayloadConvertException extends RuntimeException {

    public PayloadConvertException(Exception e) {
        super(e);
    }

}
