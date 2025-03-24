package com.personaltoy.backend.domains.order.dto.reqeust;

import com.personaltoy.backend.domains.order.domain.entity.OrderState;
import lombok.*;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
public class OrderStateUpdateDto {

    @NotNull
    private Long version;

    @NotNull
    private OrderState orderState;

}
