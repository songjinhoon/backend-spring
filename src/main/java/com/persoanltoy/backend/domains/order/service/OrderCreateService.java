package com.persoanltoy.backend.domains.order.service;

import com.persoanltoy.backend.config.security.custom.CustomUserDetails;
import com.persoanltoy.backend.domains.common.model.Money;
import com.persoanltoy.backend.domains.common.payment.PaymentStrategy;
import com.persoanltoy.backend.domains.common.payment.PaymentStrategyFactory;
import com.persoanltoy.backend.domains.common.payment.exception.KakaoStrategyException;
import com.persoanltoy.backend.domains.order.domain.entity.Order;
import com.persoanltoy.backend.domains.order.domain.entity.value.OrderLine;
import com.persoanltoy.backend.domains.order.domain.entity.value.Orderer;
import com.persoanltoy.backend.domains.order.domain.repository.OrderRepository;
import com.persoanltoy.backend.domains.order.dto.reqeust.OrderCreateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class OrderCreateService {

    private final OrderRepository orderRepository;

    /**
     * 1. 주문이랑 결제는 다른 서비스로 본다.
     * 2. 주문이랑 결제는 하나의 트랜잭션으로 본다. (주문에 성공하고 결제에 실패했을떄, 결제만 나중에 시도하는 등의 방식을 허용하지 않는다.)
     * 3. 외부 서비스로 인한 성능상의 문제, 주문과 결제 도메인의 강결합 문제 추후 고민.
     */
    @Transactional
    public Order create(OrderCreateDto orderCreateDto, CustomUserDetails user) {
        final Orderer orderer = new Orderer(user.getMemberId(), user.getNickName());
        final List<OrderLine> orderLines = orderCreateDto.getOrderProducts()
                .stream()
                .map(orderProduct ->
                        new OrderLine(orderProduct.getProductId(), new Money(100), orderProduct.getQuantity())
                )
                .collect(Collectors.toList());

        Order save = orderRepository.save(Order.create(orderer, orderLines, orderCreateDto.getShippingInfo()));

        PaymentStrategy paymentStrategy = PaymentStrategyFactory.getPaymentStrategy(orderCreateDto.getPaymentType());
        try {
            paymentStrategy.pay(save.getTotalAmounts().getValue());
        } catch (Exception e) {
            throw new KakaoStrategyException(e.getMessage());
        }

        return save;
    }

}
