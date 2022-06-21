package com.persoanltoy.backend.domain;

import lombok.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder @Getter
public class ResponseDto<T> {

    @Builder.Default
    private Object error = new HashMap<>();

    @Builder.Default
    private List<T> data = new ArrayList<>();

}
