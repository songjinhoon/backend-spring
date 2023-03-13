package com.persoanltoy.backend.config.security.custom;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

@Slf4j
@RequiredArgsConstructor
public class CustomLoginFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        StringBuilder stringBuilder = new StringBuilder();
        if (exception.getClass().equals(UsernameNotFoundException.class)) {
            stringBuilder.append("username not found");
            log.info(stringBuilder.toString());
        } else if (exception.getClass().equals(BadCredentialsException.class)) {
            stringBuilder.append("password miss match");
            log.info(stringBuilder.toString());
        }
        try (OutputStream os = response.getOutputStream()) {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(os, stringBuilder.toString());
            os.flush();
        }
    }

}
