package com.persoanltoy.backend.domains.order.domain.entity.event;

import com.persoanltoy.backend.domains.common.event.Event;
import com.persoanltoy.backend.domains.common.model.Money;
import com.persoanltoy.backend.domains.common.payment.PaymentType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
public class OrderCancelEvent extends Event {

    private String id;

    private int amount;

    private PaymentType paymentType;

    public OrderCancelEvent() {

    }

    public OrderCancelEvent(String id, Money amount, PaymentType paymentType) {
        super();
        this.id = id;
        this.amount = amount.getValue();
        this.paymentType = paymentType;
    }

}
