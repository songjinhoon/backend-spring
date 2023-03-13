package com.persoanltoy.backend.domains.order.dto.reqeust;

import com.persoanltoy.backend.domains.order.domain.entity.OrderState;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Builder
@Setter
@Getter
public class OrderStateUpdateDto {

    @NotNull
    private Long version;

    @NotNull
    private OrderState orderState;

}
