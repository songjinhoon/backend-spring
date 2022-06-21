package com.persoanltoy.backend.domain.todo.dto;

import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class TodoUpdateDto {

    @NotNull
    private Boolean done;

}
