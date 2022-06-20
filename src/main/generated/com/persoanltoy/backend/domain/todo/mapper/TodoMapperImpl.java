package com.persoanltoy.backend.domain.todo.mapper;

import com.persoanltoy.backend.domain.todo.dto.TodoSimpleDto;
import com.persoanltoy.backend.domain.todo.entity.Todo;
import com.persoanltoy.backend.domain.usr.entity.Usr;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-06-20T21:34:53+0900",
    comments = "version: 1.5.1.Final, compiler: javac, environment: Java 11.0.15 (Amazon.com Inc.)"
)
public class TodoMapperImpl implements TodoMapper {

    @Override
    public TodoSimpleDto getTodoSimpleDto(Todo todo) {
        if ( todo == null ) {
            return null;
        }

        TodoSimpleDto todoSimpleDto = new TodoSimpleDto();

        todoSimpleDto.setUsrId( todoUsrId( todo ) );
        todoSimpleDto.setRgsDt( todo.getRgsDt() );
        todoSimpleDto.setUpdDt( todo.getUpdDt() );
        todoSimpleDto.setId( todo.getId() );
        todoSimpleDto.setTitle( todo.getTitle() );
        todoSimpleDto.setDone( todo.getDone() );

        return todoSimpleDto;
    }

    private String todoUsrId(Todo todo) {
        if ( todo == null ) {
            return null;
        }
        Usr usr = todo.getUsr();
        if ( usr == null ) {
            return null;
        }
        String id = usr.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
