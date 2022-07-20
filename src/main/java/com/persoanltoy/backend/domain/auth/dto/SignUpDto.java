package com.persoanltoy.backend.domain.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter @Setter
public class SignUpDto {

    @ApiModelProperty(value = "아이디", example = "testid@jinhoon.com", required = true)
    @NotNull @Size(min = 10, max = 25)
    private String id;

    @ApiModelProperty(value = "비밀번호", example = "testid", required = true)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull @Size(min = 4, max = 20)
    private String pwd;

    @ApiModelProperty(value = "이름", example = "jinhoon", required = true)
    @NotNull @Size(min = 5, max = 10)
    private String nm;

}