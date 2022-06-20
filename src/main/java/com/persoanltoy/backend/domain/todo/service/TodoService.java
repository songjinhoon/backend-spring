package com.persoanltoy.backend.domain.todo.service;

import com.persoanltoy.backend.domain.todo.dto.TodoInsertDto;
import com.persoanltoy.backend.domain.todo.dto.TodoSimpleDto;
import com.persoanltoy.backend.domain.todo.entity.Todo;
import com.persoanltoy.backend.domain.todo.repository.TodoRepository;
import com.persoanltoy.backend.domain.usr.entity.Usr;
import com.persoanltoy.backend.domain.usr.repository.UsrRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class TodoService {

    private final TodoRepository todoRepository;

    private final UsrRepository usrRepository;

    public TodoSimpleDto findByIdToSimple(Long id) {
        return TodoSimpleDto.of(todoRepository.findById(id).orElseGet(Todo::empty));
    }

    public TodoSimpleDto save(TodoInsertDto todoInsertDto) {
        final Usr usr = usrRepository.findById(todoInsertDto.getUsrId()).orElseThrow(NoSuchElementException::new);
        return TodoSimpleDto.of(todoRepository.save(Todo.create(todoInsertDto, usr)));
    }
}
