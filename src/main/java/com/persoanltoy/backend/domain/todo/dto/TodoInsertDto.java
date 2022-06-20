package com.persoanltoy.backend.domain.todo.dto;

import lombok.Getter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
public class TodoInsertDto {

    @NotNull @Size(min = 1, max = 30)
    private String usrId;

    @NotNull @Size(min = 5, max = 20)
    private String title;

    @NotNull
    private Boolean done;

}
