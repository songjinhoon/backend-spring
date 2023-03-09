package com.persoanltoy.backend.domains.member.dto.request;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class SignInDto {

    @NotBlank
    @Size(min = 10, max = 25)
    private String username;

    @NotBlank
    @Size(min = 4, max = 20)
    private String password;

}
