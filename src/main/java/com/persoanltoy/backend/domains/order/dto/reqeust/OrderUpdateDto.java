package com.persoanltoy.backend.domains.order.dto.reqeust;

import com.persoanltoy.backend.domains.order.domain.entity.value.ShippingInfo;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Builder
@Getter
@Setter
public class OrderUpdateDto {

    @NotNull
    private Long version;

    @NotNull
    private ShippingInfo shippingInfo;

}
