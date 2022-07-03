package com.persoanltoy.backend.domain.auth.dto;

import com.persoanltoy.backend.domain.auth.entity.Auth;
import com.persoanltoy.backend.domain.auth.mapper.AuthMapper;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter @Setter
public class AuthDto {

    private String id;

    public static AuthDto of(Auth auth) {
        return AuthMapper.INSTANCE.toAuthDto(auth);
    }

    public static List<AuthDto> of(List<Auth> auths) {
        return auths.stream().map(AuthDto::of).collect(Collectors.toList());
    }

}
