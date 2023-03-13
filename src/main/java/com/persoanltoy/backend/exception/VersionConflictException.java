package com.persoanltoy.backend.exception;

public class VersionConflictException extends RuntimeException {

    public VersionConflictException(String message) {
        super(message);
    }

}
