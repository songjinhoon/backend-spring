package com.persoanltoy.backend.domains.member.dto.request;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class SignUpDto {

    @NotBlank
    @Size(min = 10, max = 25)
    private String username;

    @NotBlank
    @Size(min = 4, max = 20)
    private String password;

    @NotBlank
    @Size(min = 5, max = 10)
    private String nickName;

}
