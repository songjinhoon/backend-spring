package com.persoanltoy.backend.domain.usr.mapper;

import com.persoanltoy.backend.domain.usr.dto.UsrSimpleDto;
import com.persoanltoy.backend.domain.usr.entity.Usr;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UsrMapper {

    UsrMapper INSTANCE = Mappers.getMapper(UsrMapper.class);

    UsrSimpleDto toUsrSimpleDto(Usr usr);

}
