package com.persoanltoy.backend.domain.todo.dto;

import com.persoanltoy.backend.domain.BaseDto;
import com.persoanltoy.backend.domain.todo.entity.Todo;
import com.persoanltoy.backend.domain.todo.mapper.TodoMapper;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter @Setter
public class TodoSimpleDto extends BaseDto {

    private Long id;

    private String usrId;

    private String title;

    private Boolean done;

    public static TodoSimpleDto of(Todo todo) {
        return TodoMapper.INSTANCE.getTodoSimpleDto(todo);
    }

    public static List<TodoSimpleDto> of(List<Todo> todos) {
        return todos.stream().map(TodoSimpleDto::of).collect(Collectors.toList());
    }

}
