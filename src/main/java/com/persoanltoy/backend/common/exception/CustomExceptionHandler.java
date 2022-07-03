package com.persoanltoy.backend.common.exception;

import com.persoanltoy.backend.common.response.ResponseDto;
import com.persoanltoy.backend.common.response.ResponseMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;

@Slf4j
@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ResponseDto<?>> runtimeException(Exception e) {
        e.printStackTrace();
        return ResponseEntity.internalServerError().body(ResponseDto.builder()
                .code(ResponseMessage.ERROR_INTERNAL_SERVER_ERROR.getCode())
                .message(ResponseMessage.ERROR_INTERNAL_SERVER_ERROR.getValue())
                .build());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ResponseDto<?>> illegalArgumentException(Exception e) {
        e.printStackTrace();
        return ResponseEntity.internalServerError().body(ResponseDto.builder()
                .code(ResponseMessage.ERROR_NOT_FOUND.getCode())
                .message(ResponseMessage.ERROR_NOT_FOUND.getValue())
                .build());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDto<?>> methodArgumentNotValidException(Exception e) {
        e.printStackTrace();
        return ResponseEntity.internalServerError().body(ResponseDto.builder()
                .code(ResponseMessage.NOT_VALID_REQUEST_DATA_EXCEPTION.getCode())
                .message(ResponseMessage.NOT_VALID_REQUEST_DATA_EXCEPTION.getValue())
                .build());
    }

    @ExceptionHandler(IdDuplicationException.class)
    public ResponseEntity<ResponseDto<?>> idDuplicationException(Exception e) {
        e.printStackTrace();
        return ResponseEntity.internalServerError().body(ResponseDto.builder()
                .code(ResponseMessage.ACCOUNT_DUPLICATION.getCode())
                .message(ResponseMessage.ACCOUNT_DUPLICATION.getValue())
                .build());
    }

    @ExceptionHandler({BadCredentialsException.class, InternalAuthenticationServiceException.class})
    public ResponseEntity<ResponseDto<?>> badCredentialsException(Exception e) {
        e.printStackTrace();
        return ResponseEntity.internalServerError().body(ResponseDto.builder()
                .code(ResponseMessage.ERROR_ACCOUNT_LOGIN.getCode())
                .message(ResponseMessage.ERROR_ACCOUNT_LOGIN.getValue())
                .build());
    }

}
