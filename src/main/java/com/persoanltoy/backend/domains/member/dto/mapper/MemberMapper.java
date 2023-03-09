package com.persoanltoy.backend.domains.member.dto.mapper;

import com.persoanltoy.backend.domains.member.dto.response.MemberDto;
import com.persoanltoy.backend.domains.member.dto.response.UsrDetailDto;
import com.persoanltoy.backend.domains.member.domain.entity.Member;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MemberMapper {

    MemberMapper INSTANCE = Mappers.getMapper(MemberMapper.class);

    MemberDto toSimpleDto(Member member);

    UsrDetailDto toUsrDetailDto(Member member);

}
