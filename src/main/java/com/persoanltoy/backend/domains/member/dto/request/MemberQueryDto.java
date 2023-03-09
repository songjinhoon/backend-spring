package com.persoanltoy.backend.domains.member.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class MemberQueryDto {

    private String username;

    private String nickName;

}
