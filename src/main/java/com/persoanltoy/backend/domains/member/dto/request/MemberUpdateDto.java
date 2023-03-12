package com.persoanltoy.backend.domains.member.dto.request;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class MemberUpdateDto {

    @NotBlank
    @Size(min = 5, max = 10)
    private String nickName;

}
