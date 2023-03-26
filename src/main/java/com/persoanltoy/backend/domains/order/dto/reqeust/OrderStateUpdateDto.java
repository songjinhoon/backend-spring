package com.persoanltoy.backend.domains.order.dto.reqeust;

import com.persoanltoy.backend.domains.order.domain.entity.OrderState;
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
