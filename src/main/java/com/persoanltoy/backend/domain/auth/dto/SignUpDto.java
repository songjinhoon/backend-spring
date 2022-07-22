package com.persoanltoy.backend.domain.auth.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NoArgsConstructor @AllArgsConstructor
@Builder @Getter @Setter
public class SignUpDto {

    @ApiModelProperty(value = "아이디", example = "testid@jinhoon.com", required = true)
    @NotNull @Size(min = 10, max = 25)
    private String id;

    @ApiModelProperty(value = "비밀번호", example = "password", required = true)
//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) 테스트코드떄는 ... 우짜누..흠
    @NotNull @Size(min = 4, max = 20)
    private String pwd;

    @ApiModelProperty(value = "이름", example = "testuser", required = true)
    @NotNull @Size(min = 5, max = 10)
    private String nm;

    @ApiModelProperty(value = "권한", example = "ROLE_USER", required = true)
    @NotNull @Size(min = 5, max = 10)
    private String authId;

}
