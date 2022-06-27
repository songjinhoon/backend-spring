package com.persoanltoy.backend.domain.usr.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
public class SignInDto {

    @ApiModelProperty(value = "아이디", example = "admin@jinhoon.com", required = true)
    @NotNull @Size(min = 10, max = 25)
    private String id;

    @ApiModelProperty(value = "비밀번호", example = "admin", required = true)
    @NotNull @Size(min = 5, max = 20)
    private String pwd;

}
