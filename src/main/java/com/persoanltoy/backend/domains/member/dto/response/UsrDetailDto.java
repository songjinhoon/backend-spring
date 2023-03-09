package com.persoanltoy.backend.domains.member.dto.response;

import com.persoanltoy.backend.domains.member.domain.entity.Member;
import com.persoanltoy.backend.domains.member.dto.mapper.MemberMapper;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsrDetailDto {

    public static UsrDetailDto of(Member member) {
        final UsrDetailDto usrDetailDto = MemberMapper.INSTANCE.toUsrDetailDto(member);
//        usrDetailDto.setAuths(AuthDto.of(new ArrayList<>(member.getAuths())));
//        usrDetailDto.setTodos(TodoSimpleDto.of(usr.getTodos()));
        return usrDetailDto;
    }

}
