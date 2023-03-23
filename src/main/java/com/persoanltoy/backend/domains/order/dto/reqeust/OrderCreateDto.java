package com.persoanltoy.backend.domains.order.dto.reqeust;

import com.persoanltoy.backend.domains.common.payment.PaymentType;
import com.persoanltoy.backend.domains.order.domain.entity.value.ShippingInfo;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class OrderCreateDto {

    @NotNull
    private List<@Valid OrderProduct> orderProducts;

    @NotNull
    private @Valid ShippingInfo shippingInfo;

    @NotNull
    private PaymentType paymentType;

}
