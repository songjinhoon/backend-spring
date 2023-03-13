package com.persoanltoy.backend.domains.order.domain.entity.event;


import com.persoanltoy.backend.domains.common.event.Event;
import com.persoanltoy.backend.domains.common.model.Money;
import com.persoanltoy.backend.domains.common.payment.PaymentStrategy;
import com.persoanltoy.backend.domains.order.domain.entity.value.OrderLine;
import com.persoanltoy.backend.domains.order.domain.entity.value.Orderer;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
public class OrderCreateEvent extends Event {

    private String number;

    private Orderer orderer;

    private List<OrderLine> orderLines;

    private Money totalAmounts;

    private LocalDateTime orderDate;

    private PaymentStrategy paymentStrategy;

}
