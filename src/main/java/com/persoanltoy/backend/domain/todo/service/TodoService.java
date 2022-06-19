package com.persoanltoy.backend.domain.todo.service;

import com.persoanltoy.backend.domain.todo.dto.TodoSimpleDto;
import com.persoanltoy.backend.domain.todo.entity.Todo;
import com.persoanltoy.backend.domain.todo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TodoService {

    private final TodoRepository todoRepository;

    public TodoSimpleDto findByIdToSimple(Long id) {
        return TodoSimpleDto.of(todoRepository.findById(id).orElseGet(Todo::empty));
    }

}
