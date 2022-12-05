package com.persoanltoy.backend.domain.member.dto;

import com.persoanltoy.backend.domain.BaseDto;
import com.persoanltoy.backend.domain.member.entity.Usr;
import com.persoanltoy.backend.domain.member.mapper.UsrMapper;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter @Setter
public class UsrSimpleDto extends BaseDto {

    private String id;

    private String pwd;

    private String nm;

    public static UsrSimpleDto of(Usr usr) {
        return UsrMapper.INSTANCE.toUsrSimpleDto(usr);
    }

    public static List<UsrSimpleDto> of(List<Usr> usrs) {
        return usrs.stream().map(UsrSimpleDto::of).collect(Collectors.toList());
    }

}
