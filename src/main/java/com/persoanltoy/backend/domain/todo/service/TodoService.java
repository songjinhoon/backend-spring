package com.persoanltoy.backend.domain.todo.service;

import com.persoanltoy.backend.domain.todo.dto.TodoDetailDto;
import com.persoanltoy.backend.domain.todo.dto.TodoInsertDto;
import com.persoanltoy.backend.domain.todo.dto.TodoSimpleDto;
import com.persoanltoy.backend.domain.todo.dto.TodoUpdateDto;
import com.persoanltoy.backend.domain.todo.entity.Todo;
import com.persoanltoy.backend.domain.todo.repository.TodoRepository;
import com.persoanltoy.backend.domain.member.entity.Usr;
import com.persoanltoy.backend.domain.member.repository.UsrRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@RequiredArgsConstructor
@Service
public class TodoService {

    private final TodoRepository todoRepository;

    private final UsrRepository usrRepository;

    public TodoSimpleDto findByIdToSimple(Long id) {
        final Todo todo = todoRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        return TodoSimpleDto.of(todo);
    }

    public TodoSimpleDto save(TodoInsertDto todoInsertDto) {
        final Usr usr = usrRepository.findById(todoInsertDto.getUsrId()).orElseThrow(IllegalArgumentException::new);
        return TodoSimpleDto.of(todoRepository.save(Todo.create(todoInsertDto, usr)));
    }

    public List<TodoDetailDto> findByUsrIdToDetail(String usrId) {
        return TodoDetailDto.of(todoRepository.findByUsrIdToDetail(usrId));
    }

    public TodoSimpleDto update(Long id, TodoUpdateDto todoUpdateDto) {
        Todo todo = todoRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        todo.updateDone(todoUpdateDto.getDone());
        return TodoSimpleDto.of(todo);
    }

    public void delete(Long id) {
        Todo todo = todoRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        todoRepository.delete(todo);
    }

}
