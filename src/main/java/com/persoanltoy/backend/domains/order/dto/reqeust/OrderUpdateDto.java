package com.persoanltoy.backend.domains.order.dto.reqeust;

import com.persoanltoy.backend.domains.order.domain.entity.value.ShippingInfo;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class OrderUpdateDto {

    @NotNull
    private Long version;

    @NotNull
    private @Valid ShippingInfo shippingInfo;

}
