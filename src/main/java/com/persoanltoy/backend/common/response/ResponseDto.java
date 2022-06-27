package com.persoanltoy.backend.common.response;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Builder @Getter
public class ResponseDto<T> {

    @Builder.Default
    private String code = ResponseMessage.SUCCESS_READ.getCode();

    @Builder.Default
    private String message = ResponseMessage.SUCCESS_READ.getValue();

    @Builder.Default
    private List<T> data = new ArrayList<>();

}
