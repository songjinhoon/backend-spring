package com.persoanltoy.backend.domain.usr.mapper;

import com.persoanltoy.backend.domain.usr.dto.UsrDetailDto;
import com.persoanltoy.backend.domain.usr.dto.UsrSimpleDto;
import com.persoanltoy.backend.domain.usr.entity.Usr;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UsrMapper {

    UsrMapper INSTANCE = Mappers.getMapper(UsrMapper.class);

    UsrSimpleDto toUsrSimpleDto(Usr usr);

    UsrDetailDto toUsrDetailDto(Usr usr);

}
