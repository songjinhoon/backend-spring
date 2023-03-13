package com.persoanltoy.backend.domains.event.api;

public class PayloadConvertException extends RuntimeException {

    public PayloadConvertException(Exception e) {
        super(e);
    }

}
