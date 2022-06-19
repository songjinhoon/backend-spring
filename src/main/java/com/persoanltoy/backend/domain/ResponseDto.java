package com.persoanltoy.backend.domain;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder @Getter @Setter
public class ResponseDto<T> {

    @Builder.Default
    private Boolean error = false;

    @Builder.Default
    private int status = HttpStatus.OK.value();

    @Builder.Default
    private String message = "success";

    @Builder.Default
    private List<T> data = new ArrayList<>();

}
