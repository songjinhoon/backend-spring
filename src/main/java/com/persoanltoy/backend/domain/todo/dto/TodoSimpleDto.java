package com.persoanltoy.backend.domain.todo.dto;

import com.persoanltoy.backend.domain.BaseDto;
import com.persoanltoy.backend.domain.todo.entity.Todo;
import com.persoanltoy.backend.domain.todo.mapper.TodoMapper;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class TodoSimpleDto extends BaseDto {

    private Long id;

    private String usrId;

    private String title;

    private Boolean done;

    public static TodoSimpleDto of(Todo todo) {
        return TodoMapper.INSTANCE.getTodoSimpleDto(todo);
    }

}
