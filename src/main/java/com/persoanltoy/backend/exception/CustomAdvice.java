package com.persoanltoy.backend.exception;

import com.persoanltoy.backend.domain.ResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class CustomAdvice {

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ResponseDto<?>> noSuchElementException(){
        return ResponseEntity.ok(ResponseDto.builder()
                .error(Map.of("code", ExceptionEnum.DATA_NOT_FOUND_EXCEPTION.getCode(), "message", ExceptionEnum.DATA_NOT_FOUND_EXCEPTION.getMessage()))
                .build());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDto<?>> methodArgumentNotValidException(){
        return ResponseEntity.ok(ResponseDto.builder()
                .error(Map.of("code", ExceptionEnum.BAD_REQUEST_EXCEPTION.getCode(), "message", ExceptionEnum.BAD_REQUEST_EXCEPTION.getMessage()))
                .build());
    }

}
