package com.persoanltoy.backend.domain.todo.mapper;

import com.persoanltoy.backend.domain.todo.dto.TodoSimpleDto;
import com.persoanltoy.backend.domain.todo.entity.Todo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TodoMapper {

    TodoMapper INSTANCE = Mappers.getMapper(TodoMapper.class);

    TodoSimpleDto getTodoSimpleDto(Todo todo);

}
