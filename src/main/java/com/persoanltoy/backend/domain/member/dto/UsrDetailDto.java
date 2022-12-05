package com.persoanltoy.backend.domain.member.dto;

import com.persoanltoy.backend.domain.auth.dto.AuthDto;
import com.persoanltoy.backend.domain.todo.dto.TodoSimpleDto;
import com.persoanltoy.backend.domain.member.entity.Usr;
import com.persoanltoy.backend.domain.member.mapper.UsrMapper;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class UsrDetailDto {

    private UsrSimpleDto usr;

    private List<AuthDto> auths;

    private List<TodoSimpleDto> todos;

    public static UsrDetailDto of(Usr usr) {
        final UsrDetailDto usrDetailDto = UsrMapper.INSTANCE.toUsrDetailDto(usr);
        usrDetailDto.setAuths(AuthDto.of(new ArrayList<>(usr.getAuths())));
//        usrDetailDto.setTodos(TodoSimpleDto.of(usr.getTodos()));
        return usrDetailDto;
    }

}
