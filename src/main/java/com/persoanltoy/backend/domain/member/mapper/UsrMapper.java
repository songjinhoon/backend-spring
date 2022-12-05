package com.persoanltoy.backend.domain.member.mapper;

import com.persoanltoy.backend.domain.member.dto.UsrDetailDto;
import com.persoanltoy.backend.domain.member.dto.UsrSimpleDto;
import com.persoanltoy.backend.domain.member.entity.Usr;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UsrMapper {

    UsrMapper INSTANCE = Mappers.getMapper(UsrMapper.class);

    UsrSimpleDto toUsrSimpleDto(Usr usr);

    UsrDetailDto toUsrDetailDto(Usr usr);

}
