package com.persoanltoy.backend.domain.auth.api;

import com.persoanltoy.backend.common.response.ResponseDto;
import com.persoanltoy.backend.common.response.ResponseMessage;
import com.persoanltoy.backend.config.jwt.JwtFilter;
import com.persoanltoy.backend.config.jwt.TokenProvider;
import com.persoanltoy.backend.config.jwt.dto.TokenDto;
import com.persoanltoy.backend.domain.auth.dto.SignInDto;
import com.persoanltoy.backend.domain.auth.dto.SignUpDto;
import com.persoanltoy.backend.domain.usr.service.UsrService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Api(tags = {"Auth"})
@RequiredArgsConstructor
@RequestMapping("/auth")
@RestController
public class AuthApi {

    private final TokenProvider tokenProvider;

    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    private final UsrService usrService;

    @ApiOperation(value = "로그인", notes = "로그인")
    @PostMapping("/signin")
    public ResponseEntity<TokenDto> signin(@Valid @RequestBody SignInDto signInDto) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(signInDto.getId(), signInDto.getPwd());
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(usernamePasswordAuthenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = tokenProvider.createToken(authentication);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + token);

        return new ResponseEntity<>(new TokenDto(token), httpHeaders, HttpStatus.OK);
    }

    @ApiOperation(value = "회원가입", notes = "회원가입")
    @PostMapping("/signup")
    public ResponseEntity<ResponseDto<?>> signup(@Valid @RequestBody SignUpDto signUpDto) {
        usrService.save(signUpDto);
        return ResponseEntity.ok().body(ResponseDto.builder()
                .code(ResponseMessage.SUCCESS_CREATE.getCode())
                .message(ResponseMessage.SUCCESS_CREATE.getValue())
                .build());
    }


}