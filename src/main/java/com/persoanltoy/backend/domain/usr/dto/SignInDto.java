package com.persoanltoy.backend.domain.usr.dto;

import lombok.Getter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
public class SignInDto {

    @NotNull @Size(min = 10, max = 25)
    private String id;

    @NotNull @Size(min = 5, max = 20)
    private String pwd;

}
