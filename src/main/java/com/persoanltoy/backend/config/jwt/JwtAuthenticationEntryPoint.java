package com.persoanltoy.backend.config.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.persoanltoy.backend.domain.common.ResponseDto;
import com.persoanltoy.backend.domain.common.ResponseMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

@Slf4j
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        log.error("Error :: UnAuthorized :: " + authException.getMessage());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        try (OutputStream os = response.getOutputStream()) {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(os, ResponseDto.builder()
                    .code(ResponseMessage.ERROR_AUTHENTICATION.getCode())
                    .message(ResponseMessage.ERROR_AUTHENTICATION.getValue())
                    .build());
            os.flush();
        }
    }

}