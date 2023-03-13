package com.persoanltoy.backend.exception;

import com.persoanltoy.backend.domains.common.ResponseDto;
import com.persoanltoy.backend.domains.common.ResponseMessage;
import com.persoanltoy.backend.domains.common.payment.exception.CardStrategyException;
import com.persoanltoy.backend.domains.common.payment.exception.KakaoStrategyException;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ResponseDto<?>> illegalArgumentException(Exception e) {
        log.error(e.getMessage());
        return ResponseEntity.ok().build();
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<ResponseDto<?>> emptyResultDataAccessException(Exception e) {
        log.error(e.getMessage());
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ResponseDto<?>> constraintViolationException(Exception e) {
        log.error(e.getMessage());
        return ResponseEntity.badRequest().build();
    }

    @ExceptionHandler({KakaoStrategyException.class, CardStrategyException.class})
    public ResponseEntity<ResponseDto<?>> paymentException(Exception e) {
        log.error(e.getMessage());
        return ResponseEntity.internalServerError().body(ResponseDto.builder()
                .code(ResponseMessage.EXTERNAL_API_CONNECT_ERROR.getCode())
                .message(ResponseMessage.EXTERNAL_API_CONNECT_ERROR.getValue())
                .build());
    }

    /*
     * ObjectOptimisticLockingFailureException 는 업데이트 쿼리 커밋 시점에 버전 불일치 시 발생 -> 버전 달라진지 얼마 안되서
     * VersionConflictException 는 조회 쿼리 후 버전 불일치 시 발생 -> 버전이 달라진 후 시간경과 시
     * */
    @ExceptionHandler({VersionConflictException.class, ObjectOptimisticLockingFailureException.class})
    public ResponseEntity<ResponseDto<?>> versionConflictE(Exception e) {
        log.error(e.getMessage());
        return ResponseEntity.internalServerError().body(ResponseDto.builder()
                .code(ResponseMessage.VERSION_CONFLICT_ERROR.getCode())
                .message(ResponseMessage.VERSION_CONFLICT_ERROR.getValue())
                .build());
    }

}
