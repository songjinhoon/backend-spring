package com.persoanltoy.backend.domain.todo.dto;

import com.persoanltoy.backend.domain.todo.entity.Todo;
import com.persoanltoy.backend.domain.todo.mapper.TodoMapper;
import com.persoanltoy.backend.domain.usr.dto.UsrSimpleDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter @Setter
public class TodoDetailDto {

    private Long id;

    private UsrSimpleDto usr;

    private String title;

    private Boolean done;

    public static TodoDetailDto of(Todo todo) {
        final TodoDetailDto todoDetailDto = TodoMapper.INSTANCE.getTodoDetailDto(todo);
        todoDetailDto.setUsr(UsrSimpleDto.of(todo.getUsr()));
        return todoDetailDto;
    }

    public static List<TodoDetailDto> of(List<Todo> todos) {
        return todos.stream().map(TodoDetailDto::of).collect(Collectors.toList());
    }

}
