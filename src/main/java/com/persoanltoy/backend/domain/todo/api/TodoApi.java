package com.persoanltoy.backend.domain.todo.api;

import com.persoanltoy.backend.domain.ResponseDto;
import com.persoanltoy.backend.domain.todo.dto.TodoInsertDto;
import com.persoanltoy.backend.domain.todo.dto.TodoSimpleDto;
import com.persoanltoy.backend.domain.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;

@RequiredArgsConstructor
@RequestMapping("/todo")
@RestController
public class TodoApi {

    private final TodoService todoService;

    @GetMapping("/findByIdToSimple/{id}")
    public ResponseEntity<ResponseDto<TodoSimpleDto>> findByIdToSimple(@PathVariable Long id) {
        final TodoSimpleDto todoSimpleDto = todoService.findByIdToSimple(id);
        return ResponseEntity.ok(ResponseDto.<TodoSimpleDto>builder()
                .data(Collections.singletonList(todoSimpleDto))
                .build());
    }

    @PostMapping("/save")
    public ResponseEntity<ResponseDto<TodoSimpleDto>> save(@Valid @RequestBody TodoInsertDto todoInsertDto) {
        final TodoSimpleDto todoSimpleDto = todoService.save(todoInsertDto);
        return ResponseEntity.ok(ResponseDto.<TodoSimpleDto>builder()
                .data(Collections.singletonList(todoSimpleDto))
                .build());
    }

}
