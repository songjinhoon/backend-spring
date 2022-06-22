package com.persoanltoy.backend.domain.usr.dto;

import com.persoanltoy.backend.domain.BaseDto;
import com.persoanltoy.backend.domain.usr.entity.Usr;
import com.persoanltoy.backend.domain.usr.mapper.UsrMapper;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UsrSimpleDto extends BaseDto {

    private String id;

    private String pwd;

    private String nm;

    public static UsrSimpleDto of(Usr usr) {
        return UsrMapper.INSTANCE.toUsrSimpleDto(usr);
    }

}
