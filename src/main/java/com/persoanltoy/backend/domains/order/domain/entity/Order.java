package com.persoanltoy.backend.domains.order.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.persoanltoy.backend.domains.common.BaseTimeEntity;
import com.persoanltoy.backend.domains.common.event.Events;
import com.persoanltoy.backend.domains.common.model.Money;
import com.persoanltoy.backend.domains.common.model.MoneyConverter;
import com.persoanltoy.backend.domains.common.payment.PaymentType;
import com.persoanltoy.backend.domains.order.domain.entity.event.OrderCancelEvent;
import com.persoanltoy.backend.domains.order.domain.entity.value.OrderLine;
import com.persoanltoy.backend.domains.order.domain.entity.value.Orderer;
import com.persoanltoy.backend.domains.order.domain.entity.value.ShippingInfo;
import com.persoanltoy.backend.domains.order.domain.exception.AlreadyShippedException;
import com.persoanltoy.backend.domains.order.dto.reqeust.OrderStateUpdateDto;
import com.persoanltoy.backend.domains.order.dto.reqeust.OrderUpdateDto;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@JsonIgnoreProperties({"number"})
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder(access = AccessLevel.PROTECTED)
@Table(name = "tn_order")
@Entity
public class Order extends BaseTimeEntity {

    @Id
    @Column(name = "order_id")
    private String id;

    // 비선점 잠금을 이용하여 트랙재션 충돌 방지 용도
    @Version
    private Long version;

    @Embedded
    private Orderer orderer;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "tn_order_line", joinColumns = @JoinColumn(name = "order_id"))
    @OrderColumn(name = "no")
    private List<OrderLine> orderLines;

    @Convert(converter = MoneyConverter.class)
    private Money totalAmounts;

    @Embedded
    private ShippingInfo shippingInfo;

    @Enumerated(EnumType.STRING)
    private OrderState orderState;

    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;

    public static Order create(Orderer orderer, List<OrderLine> orderLines, ShippingInfo shippingInfo, PaymentType paymentType) {
        return Order.builder()
                .id(UUID.randomUUID().toString())
                .orderer(orderer)
                .orderLines(orderLines)
                .totalAmounts(new Money(orderLines.stream().mapToInt(data -> data.getAmounts().getValue()).sum()))
                .shippingInfo(shippingInfo)
                .orderState(OrderState.PAYMENT_WAITING)
                .orderDate(LocalDateTime.now())
                .paymentType(paymentType)
                .build();
    }

    public boolean matchVersion(Long version) {
        return this.version.equals(version);
    }

    public void update(OrderUpdateDto orderUpdateDto) {
        if (!this.isNotYetShipped()) {
            throw new AlreadyShippedException();
        }
        this.shippingInfo = orderUpdateDto.getShippingInfo();
    }

    public void update(OrderStateUpdateDto orderUpdateDto) {
        this.orderState = orderUpdateDto.getOrderState();
    }

    public void cancel() {
        if (!this.isNotYetShipped()) {
            throw new AlreadyShippedException();
        }
        this.orderState = OrderState.CANCELED;
        Events.raise(new OrderCancelEvent(this.id, this.totalAmounts, this.paymentType));
    }

    public boolean isNotYetShipped() {
        return orderState == OrderState.PAYMENT_WAITING || orderState == OrderState.PREPARING;
    }

}
