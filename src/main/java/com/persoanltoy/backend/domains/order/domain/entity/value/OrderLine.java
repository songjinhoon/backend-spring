package com.persoanltoy.backend.domains.order.domain.entity.value;

import com.persoanltoy.backend.domains.common.model.Money;
import com.persoanltoy.backend.domains.common.model.MoneyConverter;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Convert;
import javax.persistence.Embeddable;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class OrderLine {

    private String productId;

    @Convert(converter = MoneyConverter.class)
    private Money price;

    private int quantity;

    @Convert(converter = MoneyConverter.class)
    private Money amounts;

    public OrderLine(String productId, Money price, int quantity) {
        this.productId = productId;
        this.price = price;
        this.quantity = quantity;
        this.amounts = price.multiply(quantity);
    }

}
