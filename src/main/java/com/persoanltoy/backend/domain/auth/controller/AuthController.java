package com.persoanltoy.backend.domain.auth.controller;

import com.persoanltoy.backend.domain.common.ResponseDto;
import com.persoanltoy.backend.domain.common.ResponseMessage;
import com.persoanltoy.backend.config.jwt.JwtFilter;
import com.persoanltoy.backend.config.jwt.TokenProvider;
import com.persoanltoy.backend.domain.auth.dto.SignInDto;
import com.persoanltoy.backend.domain.auth.dto.SignUpDto;
import com.persoanltoy.backend.domain.member.service.UsrService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
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
import java.util.Collections;

@Api(tags = {"Auth"})
@RequiredArgsConstructor
@RequestMapping("/auth")
@RestController
public class AuthController {

    private final TokenProvider tokenProvider;

    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    private final UsrService usrService;

    @ApiOperation(value = "로그인", notes = "로그인")
    @PostMapping("/signin")
    public ResponseEntity<ResponseDto<?>> signin(@Valid @RequestBody SignInDto signInDto) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(signInDto.getId(), signInDto.getPwd());
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(usernamePasswordAuthenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = tokenProvider.createToken(authentication);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + token);

        return new ResponseEntity<>(ResponseDto.builder()
                .data(Collections.singletonList(usrService.findById(signInDto.getId())))
                .build(), httpHeaders, HttpStatus.OK);
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