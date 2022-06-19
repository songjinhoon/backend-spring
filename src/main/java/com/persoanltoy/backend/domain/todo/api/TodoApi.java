package com.persoanltoy.backend.domain.todo.api;

import com.persoanltoy.backend.domain.ResponseDto;
import com.persoanltoy.backend.domain.todo.dto.TodoSimpleDto;
import com.persoanltoy.backend.domain.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RequiredArgsConstructor
@RequestMapping("/todo")
@RestController
public class TodoApi {

    private final TodoService todoService;

    @GetMapping("/test")
    public String test() {
        return "test";
    }

    @GetMapping("/findByIdToSimple/{id}")
    public ResponseEntity<ResponseDto<TodoSimpleDto>> findByIdToSimple(@PathVariable Long id) {
        final TodoSimpleDto todoSimpleDto = todoService.findByIdToSimple(id);
        return ResponseEntity.ok(ResponseDto.<TodoSimpleDto>builder()
                .data(Collections.singletonList(todoSimpleDto))
                .build());
    }

}
