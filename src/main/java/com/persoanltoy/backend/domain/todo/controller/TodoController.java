package com.persoanltoy.backend.domain.todo.controller;

import com.persoanltoy.backend.domain.common.ResponseDto;
import com.persoanltoy.backend.domain.common.ResponseMessage;
import com.persoanltoy.backend.domain.todo.dto.TodoUpdateDto;
import com.persoanltoy.backend.domain.todo.dto.TodoDetailDto;
import com.persoanltoy.backend.domain.todo.dto.TodoInsertDto;
import com.persoanltoy.backend.domain.todo.dto.TodoSimpleDto;
import com.persoanltoy.backend.domain.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RequestMapping("/todo")
@RestController
public class TodoController {

    private final TodoService todoService;

    @GetMapping("/findByIdToSimple/{id}")
    public ResponseEntity<ResponseDto<TodoSimpleDto>> findByIdToSimple(@PathVariable Long id) {
        final TodoSimpleDto todoSimpleDto = todoService.findByIdToSimple(id);
        return ResponseEntity.ok(ResponseDto.<TodoSimpleDto>builder()
                .data(Collections.singletonList(todoSimpleDto))
                .build());
    }

    @GetMapping("/findByUsrIdToDetail/{usrId}")
    public ResponseEntity<ResponseDto<TodoDetailDto>> findByUsrIdToDetail(@PathVariable String usrId) {
        final List<TodoDetailDto> todoDetailDto = todoService.findByUsrIdToDetail(usrId);
        return ResponseEntity.ok().body(ResponseDto.<TodoDetailDto>builder()
                .data(todoDetailDto)
                .build());
    }

    @PostMapping("/save")
    public ResponseEntity<ResponseDto<TodoSimpleDto>> save(@Valid @RequestBody TodoInsertDto todoInsertDto) {
        final TodoSimpleDto todoSimpleDto = todoService.save(todoInsertDto);
        return ResponseEntity.ok(ResponseDto.<TodoSimpleDto>builder()
                .data(Collections.singletonList(todoSimpleDto))
                .build());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseDto<TodoSimpleDto>> update(@PathVariable Long id, @Valid @RequestBody TodoUpdateDto todoUpdateDto) {
        final TodoSimpleDto todoSimpleDto = todoService.update(id, todoUpdateDto);
        return ResponseEntity.ok(ResponseDto.<TodoSimpleDto>builder()
                .data(Collections.singletonList(todoSimpleDto))
                .build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDto<Map<String, String>>> delete(@PathVariable Long id) {
        todoService.delete(id);
        return ResponseEntity.ok(ResponseDto.<Map<String, String>>builder()
                .data(Collections.singletonList(Map.of("message", ResponseMessage.SUCCESS_DELETE.getValue())))
                .build());
    }

}
