package com.persoanltoy.backend.config.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.persoanltoy.backend.domains.common.ResponseDto;
import com.persoanltoy.backend.domains.common.ResponseMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

@Slf4j
@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
        log.error("Error :: Forbidden :: " + accessDeniedException.getMessage());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpStatus.FORBIDDEN.value());
        try (OutputStream os = response.getOutputStream()) {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(os, ResponseDto.builder()
                    .code(ResponseMessage.ERROR_FORBIDDEN.getCode())
                    .message(ResponseMessage.ERROR_FORBIDDEN.getValue())
                    .build());
            os.flush();
        }
    }

}
