package com.persoanltoy.backend.config.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.persoanltoy.backend.config.AuthConstants;
import com.persoanltoy.backend.config.jwt.TokenProvider;
import com.persoanltoy.backend.config.security.custom.CustomUserDetails;
import com.persoanltoy.backend.domains.member.dto.request.SignInDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final TokenProvider tokenProvider;

    public CustomAuthenticationFilter(final AuthenticationManager authenticationManager, final TokenProvider tokenProvider) {
        super.setAuthenticationManager(authenticationManager);
        this.tokenProvider = tokenProvider;
    }

    @Override
    public Authentication attemptAuthentication(final HttpServletRequest request, final HttpServletResponse response) throws AuthenticationException {
        try {
            SignInDto signInDto = new ObjectMapper().readValue(request.getReader(), SignInDto.class);
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(signInDto.getUsername(), signInDto.getPassword());
            setDetails(request, usernamePasswordAuthenticationToken);
            return this.getAuthenticationManager().authenticate(usernamePasswordAuthenticationToken);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        final String token = tokenProvider.createToken(authResult);

        // Refresh Token
        response.addHeader("Set-Cookie", tokenProvider.createRefreshToken(token).toString());

        // Access Token
        response.addHeader(AuthConstants.AUTHORIZATION_HEADER, AuthConstants.TOKEN_TYPE + token);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        CustomUserDetails userDetails = (CustomUserDetails) authResult.getPrincipal();

        response.setStatus(HttpServletResponse.SC_OK);
    }

}
