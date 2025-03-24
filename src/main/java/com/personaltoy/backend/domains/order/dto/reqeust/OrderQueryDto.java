package com.personaltoy.backend.domains.order.dto.reqeust;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderQueryDto {

    private String ordererName;

    private String receiverName;

}
