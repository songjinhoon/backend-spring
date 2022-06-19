package com.persoanltoy.backend.domain.todo.mapper;

import com.persoanltoy.backend.domain.todo.dto.TodoSimpleDto;
import com.persoanltoy.backend.domain.todo.entity.Todo;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-06-19T18:18:40+0900",
    comments = "version: 1.5.1.Final, compiler: javac, environment: Java 11.0.15 (Amazon.com Inc.)"
)
public class TodoMapperImpl implements TodoMapper {

    @Override
    public TodoSimpleDto getTodoSimpleDto(Todo todo) {
        if ( todo == null ) {
            return null;
        }

        TodoSimpleDto todoSimpleDto = new TodoSimpleDto();

        todoSimpleDto.setRgsDt( todo.getRgsDt() );
        todoSimpleDto.setUpdDt( todo.getUpdDt() );
        todoSimpleDto.setId( todo.getId() );
        todoSimpleDto.setUsrId( todo.getUsrId() );
        todoSimpleDto.setTitle( todo.getTitle() );
        todoSimpleDto.setDone( todo.getDone() );

        return todoSimpleDto;
    }
}
