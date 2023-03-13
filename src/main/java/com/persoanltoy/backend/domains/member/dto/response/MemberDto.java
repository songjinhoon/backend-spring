package com.persoanltoy.backend.domains.member.dto.response;

import com.persoanltoy.backend.domains.common.BaseDto;
import com.persoanltoy.backend.domains.member.domain.entity.Member;
import com.persoanltoy.backend.domains.member.dto.mapper.MemberMapper;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
@Setter
public class MemberDto extends BaseDto {

    private UUID id;

    private String username;

    private String nickName;

    public static MemberDto of(Member member) {
        MemberDto memberDto = MemberMapper.INSTANCE.toMemberDto(member);
        memberDto.setId(member.getNumber().getId());
        return memberDto;
    }

    public static List<MemberDto> of(List<Member> members) {
        return members.stream().map(MemberDto::of).collect(Collectors.toList());
    }

}
