package com.persoanltoy.backend.domain.auth.mapper;

import com.persoanltoy.backend.domain.auth.dto.AuthDto;
import com.persoanltoy.backend.domain.auth.entity.Auth;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AuthMapper {

    AuthMapper INSTANCE = Mappers.getMapper(AuthMapper.class);

    AuthDto toAuthDto(Auth auth);

}
